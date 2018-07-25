package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_CONNECTION_SUCCESSFUL;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_LOGIN_FAILED;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class SyncPresenter extends SyncHelper implements SyncContract.Presenter {
	
	private Thread connectThread;
	
	public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
		super();
		this.syncDashboard = syncDashboard;
	}
	
	@Override
	public void getCredentials() {
		addPreloader();
		try {
			AppAuthCredentials connectionData = authCredentialsDbService.getAppAuthCredentials();
			if (connectionData != null) {
				syncDashboard.updateUiFields(connectionData);
			}
		} finally {
			removePreloader();
		}
	}
	
	/**
	 * Saves the user database connections in the sqlite database
	 *
	 * @param serverAddress
	 * @param serverPort
	 * @param databaseName
	 * @param databaseUsername
	 * @param databasePassword
	 * @param syncPeriod
	 * @param syncPeriodUnit
	 * @throws SQLException
	 */
	@Override
	public void saveDbAuth(String serverAddress, String serverPort, String databaseName,
	                       String databaseUsername, String databasePassword, String syncPeriod,
	                       String syncPeriodUnit) throws SQLException {
		addPreloader();
		try {
			AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
			appAuthCredentials.setServerAddress(serverAddress);
			appAuthCredentials.setServerPort(Integer.parseInt(serverPort));
			appAuthCredentials.setDatabaseName(databaseName);
			appAuthCredentials.setDatabaseUsername(databaseUsername);
			appAuthCredentials.setDatabasePassword(databasePassword);
			appAuthCredentials.setSyncPeriod(Integer.parseInt(syncPeriod));
			appAuthCredentials.setSyncPeriodUnit(syncPeriodUnit);
			authCredentialsDbService.save(appAuthCredentials);
		} finally {
			removePreloader();
		}
	}
	
	/**
	 * Saves the salesforce authentication details in the sqlite database
	 *
	 * @param salesforceClientId
	 * @param salesforceClientSecret
	 * @param salesforceUsername
	 * @param salesforcePassword
	 * @param salesforceSecurityToken
	 * @throws SQLException
	 */
	@Override
	public void saveSalesforceAuth(String salesforceClientId, String salesforceClientSecret,
	                               String salesforceUsername, String salesforcePassword,
	                               String salesforceSecurityToken) throws SQLException {
		addPreloader();
		try {
			AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
			appAuthCredentials.setSalesforceClientId(salesforceClientId);
			appAuthCredentials.setSalesforceClientSecret(salesforceClientSecret);
			appAuthCredentials.setSalesforceUsername(salesforceUsername);
			appAuthCredentials.setSalesforcePassword(salesforcePassword);
			appAuthCredentials.setSalesforceSecurityToken(salesforceSecurityToken);
			authCredentialsDbService.save(appAuthCredentials);
		} finally {
			removePreloader();
		}
	}
	
	@Override
	public void testDbConnection(String serverAddress, String serverPort, String databaseName,
	                             String databaseUsername, String databasePassword) {
		addPreloader();
		
		final boolean[] connectionSuccessful = {false};
		
		connectThread = new Thread(() -> {
			try {
				if (sapDbService.testServerConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword)) {
					connectionSuccessful[0] = true;
				}
			} catch (ClassNotFoundException | SQLException e) {
				syncDashboard.showErrorMessage(e.getMessage());
			} finally {
				removePreloader();
				if (connectionSuccessful[0]) {
					syncDashboard.showSuccessMessage(
							getString(MESSAGE_CONNECTION_SUCCESSFUL));
				}
				try {
					connectThread.join();
				} catch (Exception ignored) {
				}
			}
		});
		connectThread.start();
	}
	
	@Override
	public void testSalesforceAuth(String salesforceClientId, String salesforceClientSecret,
	                               String salesforceUsername, String salesforcePassword,
	                               String salesforceSecurityToken) {
		
		addPreloader();
		
		DataService.GetCallback<SalesforceAuthCredentials> authCallback = new DataService.GetCallback<SalesforceAuthCredentials>() {
			@Override
			public void onCompleted(SalesforceAuthCredentials serverResponse) {
				saveSalesforceCredentials(serverResponse);
				syncDashboard.showSuccessMessage(getString(MESSAGE_CONNECTION_SUCCESSFUL));
			}
			
			@Override
			public void onError(Throwable t) {
				removePreloader();
				syncDashboard.showErrorMessage(getString(MESSAGE_LOGIN_FAILED),
						t.getLocalizedMessage());
			}
			
			@Override
			public void always() {
				removePreloader();
			}
		};
		
		connectThread = new Thread(() -> {
			try {
				getAuthService().authenticate(salesforceClientId, salesforceClientSecret, salesforceUsername, salesforcePassword,
						salesforceSecurityToken, authCallback);
			} catch (Exception e) {
				removePreloader();
				syncDashboard.showErrorMessage(getString(MESSAGE_LOGIN_FAILED), e.getMessage());
			} finally {
				try {
					connectThread.join();
				} catch (Exception ignored) {
				}
			}
		});
		connectThread.start();
	}
	
	@Override
	public void performSync() {
		//Check if we have a session token, if we don't we use the credentials to get one
		if (hasAccessToken()) {
			fetchFromTheServer();
		} else {
			requestAccessToken();
		}
	}
	
	private void requestAccessToken() {
		addPreloader();
		if (hasCredentials()) {
			AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
			DataService.GetCallback<SalesforceAuthCredentials> authCallback = new DataService.GetCallback<SalesforceAuthCredentials>() {
				@Override
				public void onCompleted(SalesforceAuthCredentials serverResponse) {
					saveSalesforceCredentials(serverResponse);
					performSync();
				}
				
				@Override
				public void onError(Throwable t) {
					AppLogger.logInfo("Failed to get Salesforce access token. " + t.getMessage());
				}
				
				@Override
				public void always() {
					removePreloader();
				}
			};
			
			addPreloader();
			connectThread = new Thread(() -> {
				try {
					getAuthService().authenticate(credentials.getSalesforceClientId(), credentials.getSalesforceClientSecret(),
							credentials.getSalesforceUsername(),
							credentials.getSalesforcePassword(),
							credentials.getSalesforceSecurityToken(), authCallback);
				} catch (Exception e) {
					AppLogger.logInfo("An error occurred when trying to get an access token. " + e.getMessage());
				} finally {
					RestServiceBuilder.switchToSalesforceApiBaseUrl();
					try {
						connectThread.join();
					} catch (Exception ignored) {
					}
				}
			});
			connectThread.start();
		} else {
			AppLogger.logInfo("Cannot get access token from Salesforce. No Login Credentials Found");
		}
	}
}
