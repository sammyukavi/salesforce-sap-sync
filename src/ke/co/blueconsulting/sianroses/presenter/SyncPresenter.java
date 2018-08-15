package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SAPDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.sync.Accounts;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_CONNECTION_SUCCESSFUL;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_LOGIN_FAILED;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class SyncPresenter implements SyncContract.Presenter {
	
	private final SAPDbService sapDbService;
	private Thread connectThread;
	private SyncContract.View syncDashboard;
	private AuthCredentialsDbService authCredentialsDbService;
	
	
	public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
		this.syncDashboard = syncDashboard;
		this.authCredentialsDbService = new AuthCredentialsDbService();
		this.sapDbService = new SAPDbService();
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
	 * Saves the Salesforce authentication details in the sqlite database
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
	public void testDbConnection(String serverAddress, String serverPort, String databaseName,
	                             String databaseUsername, String databasePassword) {
		syncDashboard.setIsBusy(true);
		
		final boolean[] connectionSuccessful = {false};
		
		connectThread = new Thread(() -> {
			try {
				if (sapDbService.testServerConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword)) {
					connectionSuccessful[0] = true;
				}
			} catch (ClassNotFoundException | SQLException e) {
				syncDashboard.showErrorMessage(e.getMessage());
			} finally {
				syncDashboard.setIsBusy(false);
				if (connectionSuccessful[0]) {
					syncDashboard.showSuccessMessage(getString(MESSAGE_CONNECTION_SUCCESSFUL));
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
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<SalesforceAuthCredentials> authCallback = new DataService.GetCallback<SalesforceAuthCredentials>() {
			@Override
			public void onCompleted(SalesforceAuthCredentials response) {
				saveSalesforceCredentials(response);
				syncDashboard.showSuccessMessage(getString(MESSAGE_CONNECTION_SUCCESSFUL));
			}
			
			@Override
			public void onError(Throwable t) {
				syncDashboard.showErrorMessage(getString(MESSAGE_LOGIN_FAILED), t.getMessage());
			}
			
			@Override
			public void always() {
				syncDashboard.setIsBusy(false);
			}
		};
		
		connectThread = new Thread(() -> {
			try {
				getAuthService().authenticate(salesforceClientId, salesforceClientSecret, salesforceUsername, salesforcePassword,
						salesforceSecurityToken, authCallback);
			} catch (Exception e) {
				syncDashboard.setIsBusy(false);
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
	
	
	private boolean hasAccessToken() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getSalesforceAccessToken())
				&& !StringUtils.isBlank(credentials.getSalesforceAccessToken());
	}
	
	public boolean hasCredentials() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getServerAddress())
				&& !StringUtils.isBlank(credentials.getServerAddress())
				&& !StringUtils.isNullOrEmpty(String.valueOf(credentials.getServerPort()))
				&& !StringUtils.isBlank(String.valueOf(credentials.getServerPort()))
				&& !StringUtils.isNullOrEmpty(credentials.getDatabaseName())
				&& !StringUtils.isBlank(credentials.getDatabaseName())
				&& !StringUtils.isNullOrEmpty(credentials.getDatabaseUsername())
				&& !StringUtils.isBlank(credentials.getDatabaseUsername())
				&& !StringUtils.isNullOrEmpty(credentials.getDatabasePassword())
				&& !StringUtils.isBlank(credentials.getDatabasePassword())
				&& !StringUtils.isNullOrEmpty(String.valueOf(credentials.getSyncPeriod()))
				&& !StringUtils.isBlank(String.valueOf(credentials.getSyncPeriod()))
				&& !StringUtils.isNullOrEmpty(credentials.getSyncPeriodUnit())
				&& !StringUtils.isBlank(credentials.getSyncPeriodUnit())
				&& !StringUtils.isNullOrEmpty(credentials.getSalesforceClientId())
				&& !StringUtils.isBlank(credentials.getSalesforceClientId())
				&& !StringUtils.isNullOrEmpty(credentials.getSalesforceClientSecret())
				&& !StringUtils.isBlank(credentials.getSalesforceClientSecret())
				&& !StringUtils.isNullOrEmpty(credentials.getSalesforceUsername())
				&& !StringUtils.isBlank(credentials.getSalesforceUsername())
				&& !StringUtils.isNullOrEmpty(credentials.getSalesforcePassword())
				&& !StringUtils.isBlank(credentials.getSalesforcePassword())
				&& !StringUtils.isNullOrEmpty(credentials.getSalesforceSecurityToken())
				&& !StringUtils.isBlank(credentials.getSalesforceSecurityToken());
	}
	
	private AuthDataService getAuthService() {
		RestServiceBuilder.switchToAuthUrl();
		return new AuthDataService();
	}
	
	private void saveSalesforceCredentials(SalesforceAuthCredentials salesforceAuthCredentials) {
		AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
		appAuthCredentials.setSalesforceAccessToken(salesforceAuthCredentials.getAccessToken());
		appAuthCredentials.setInstanceUrl(salesforceAuthCredentials.getInstanceUrl());
		appAuthCredentials.setSalesForceId(salesforceAuthCredentials.getId());
		appAuthCredentials.setTokenType(salesforceAuthCredentials.getTokenType());
		appAuthCredentials.setIssuedAt(salesforceAuthCredentials.getIssuedAt());
		appAuthCredentials.setSignature(salesforceAuthCredentials.getSignature());
		try {
			authCredentialsDbService.save(appAuthCredentials);
			AppLogger.logInfo("Salesforce Credentials Successfully Stored");
		} catch (SQLException e) {
			AppLogger.logError("Failed to store Salesforce Credentials. " + e.getMessage());
		}
	}
	
	@Override
	public void performSync() {
		if (hasAccessToken()) {
			RestServiceBuilder.switchToApiBaseUrl();
			SyncDataService syncDataService = new SyncDataService();
			SAPDbService sapDbService = new SAPDbService();
			Accounts.sync(syncDashboard, syncDataService, sapDbService);
		} else {
			syncDashboard.showSuccessMessage("Cannot get access token from Salesforce. No Login Credentials Found");
		}
	}
}
