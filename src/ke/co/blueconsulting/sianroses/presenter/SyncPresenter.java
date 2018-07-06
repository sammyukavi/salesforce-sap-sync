package ke.co.blueconsulting.sianroses.presenter;


import com.sun.istack.internal.NotNull;
import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SyncDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.Console;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.*;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class SyncPresenter implements SyncContract.Presenter {
	private SyncContract.View syncDashboard;
	private AuthCredentialsDbService authCredentialsDbService;
	private AuthDataService authDataService;
	private SyncDataService syncDataService;
	private SyncDbService syncDbService;
	private Thread connectThread;
	
	
	public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
		this.syncDashboard = syncDashboard;
		this.authCredentialsDbService = new AuthCredentialsDbService();
		this.syncDbService = new SyncDbService();
		this.syncDataService = new SyncDataService();
		
		//Switch url for auth purposes...Must be put after syncDataService has been initialized
		RestServiceBuilder.switchToSalesforceAuthUrl();
		this.authDataService = new AuthDataService();
	}
	
	@Override
	public void getCredentials() {
		syncDashboard.setIsBusy(true);
		try {
			AppAuthCredentials connectionData = authCredentialsDbService.getAppAuthCredentials();
			if (connectionData != null) {
				syncDashboard.updateUiFields(connectionData);
			}
		} finally {
			syncDashboard.setIsBusy(false);
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
		syncDashboard.setIsBusy(true);
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
			syncDashboard.setIsBusy(false);
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
		syncDashboard.setIsBusy(true);
		try {
			AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
			appAuthCredentials.setSalesforceClientId(salesforceClientId);
			appAuthCredentials.setSalesforceClientSecret(salesforceClientSecret);
			appAuthCredentials.setSalesforceUsername(salesforceUsername);
			appAuthCredentials.setSalesforcePassword(salesforcePassword);
			appAuthCredentials.setSalesforceSecurityToken(salesforceSecurityToken);
			authCredentialsDbService.save(appAuthCredentials);
		} finally {
			syncDashboard.setIsBusy(false);
		}
	}
	
	@Override
	public void testDbConnection(@NotNull String serverAddress, String serverPort, String databaseName,
	                             String databaseUsername, String databasePassword) {
		final boolean[] connectionSuccessful = {false};
		syncDashboard.setIsBusy(true);
		connectThread = new Thread(() -> {
			try {
				if (syncDbService.testServerConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword)) {
					connectionSuccessful[0] = true;
				}
			} catch (ClassNotFoundException | SQLException e) {
				syncDashboard.setIsBusy(false);
				syncDashboard.showErrorMessage(e.getMessage());
			} finally {
				syncDashboard.setIsBusy(false);
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
	public void performSync() throws SQLException {
		//Check if we have a session token, if we don't we use the credentials to get one
		if (hasAccessToken()) {
			fetchFromTheServer();
		} else {
			requestAccessToken();
		}
	}
	
	private void requestAccessToken() {
		/*if (hasCredentials()) {
			AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
			DataService.GetCallback<AppAuthCredentials> authCallback = new DataService.GetCallback<AppAuthCredentials>() {
				@Override
				public void onCompleted(AppAuthCredentials serverResponse) {
					Console.dump(serverResponse);
					removePreloader();
					try {
						performSync();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onError(Throwable t) {
					removePreloader();
					syncDashboard.showErrorMessage(getString(MESSAGE_LOGIN_FAILED),
							getString(MESSAGE_INVALID_SALESFORCE_CREDENTIALS));
				}
			};
			
			syncDashboard.setIsBusy(true);
			connectThread = new Thread(() -> {
				try {
					RestServiceBuilder.switchToSalesforceAuthUrl();
					authDataService.authenticate(credentials.getSalesforceClientId(), credentials.getSalesforceClientSecret(),
							credentials.getSalesforceUsername(),
							credentials.getSalesforcePassword(),
							credentials.getSalesforceSecurityToken(), authCallback);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					RestServiceBuilder.switchToSalesforceBaseUrl();
					try {
						connectThread.join();
					} catch (Exception ignored) {
					}
				}
			});
			connectThread.start();
		}*/
	}
	
	private boolean hasAccessToken() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getSalesforceAccessToken()) &&
				!StringUtils.isBlank(credentials.getSalesforceAccessToken());
	}
	
	public boolean hasCredentials() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getSalesforceClientId()) &&
				!StringUtils.isBlank(credentials.getSalesforceClientId()) &&
				!StringUtils.isNullOrEmpty(credentials.getSalesforceClientSecret()) &&
				!StringUtils.isBlank(credentials.getSalesforceClientSecret()) &&
				!StringUtils.isNullOrEmpty(credentials.getSalesforceUsername()) &&
				!StringUtils.isBlank(credentials.getSalesforceUsername()) &&
				!StringUtils.isNullOrEmpty(credentials.getSalesforcePassword()) &&
				!StringUtils.isBlank(credentials.getSalesforcePassword()) &&
				!StringUtils.isNullOrEmpty(credentials.getSalesforceSecurityToken()) &&
				!StringUtils.isBlank(credentials.getSalesforceSecurityToken());
	}
	
	private void fetchFromTheServer() {
		DataService.GetCallback<ServerResponse> getFromTheServerCallback = new DataService.GetCallback<ServerResponse>() {
			@Override
			public void onCompleted(ServerResponse serverResponse) {
				System.out.println(serverResponse.toString());
			}
			
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		};
		syncDataService.getFromServer(getFromTheServerCallback);
	}
	
	private void sendToTheServer() throws SQLException {
		
		DataService.GetCallback<ServerResponse> postToserverCallback = new DataService.GetCallback<ServerResponse>() {
			@Override
			public void onCompleted(ServerResponse serverResponse) {
				Console.dump(serverResponse);
			}
			
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		};
		List<Customer> customerList = syncDbService.getUnsyncedRecords(Customer.class);
		ServerResponse serverResponse = new ServerResponse();
		serverResponse.addData("customers", customerList);
		syncDataService.postToServer(serverResponse, postToserverCallback);
	}
	
	
	private void removePreloader() {
		syncDashboard.setIsBusy(false);
	}
	
	@Override
	public void testSalesforceAuth(String salesforceClientId, String salesforceClientSecret,
	                               String salesforceUsername, String salesforcePassword,
	                               String salesforceSecurityToken) {
		
		DataService.GetCallback<SalesforceAuthCredentials> authCallback = new DataService.GetCallback<SalesforceAuthCredentials>() {
			@Override
			public void onCompleted(SalesforceAuthCredentials serverResponse) {
				/*AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
				credentials.setSalesforceAccessToken(serverResponse.getSalesforceAccessToken());
				try {
					authCredentialsDbService.save(credentials);
				} catch (SQLException e) {
					e.printStackTrace();
				}*/
				Console.dump(serverResponse);
				removePreloader();
				syncDashboard.showSuccessMessage(getString(MESSAGE_CONNECTION_SUCCESSFUL));
			}
			
			@Override
			public void onError(Throwable t) {
				removePreloader();
				syncDashboard.showErrorMessage(getString(MESSAGE_LOGIN_FAILED),
						getString(MESSAGE_INVALID_SALESFORCE_CREDENTIALS));
			}
		};
		
		syncDashboard.setIsBusy(true);
		connectThread = new Thread(() -> {
			try {
				authDataService = new AuthDataService();
				authDataService.authenticate(salesforceClientId, salesforceClientSecret, salesforceUsername, salesforcePassword,
						salesforceSecurityToken, authCallback);
			} catch (Exception e) {
				e.printStackTrace();
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
}
