package ke.co.blueconsulting.sianroses.presenter;

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

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_INVALID_SALESFORCE_CREDENTIALS;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_LOGIN_FAILED;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

class SyncHelper {
	SyncContract.View syncDashboard;
	AuthCredentialsDbService authCredentialsDbService;
	AuthDataService authDataService;
	SyncDataService syncDataService;
	SyncDbService syncDbService;
	
	void removePreloader() {
		syncDashboard.setIsBusy(false);
	}
	
	boolean hasAccessToken() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getSalesforceAccessToken()) &&
				!StringUtils.isBlank(credentials.getSalesforceAccessToken());
	}
	
	boolean hasCredentials() {
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
	
	void saveSalesforceCredentials(SalesforceAuthCredentials salesforceAuthCredentials) {
		AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
		appAuthCredentials.setSalesforceAccessToken(salesforceAuthCredentials.getAccessToken());
		appAuthCredentials.setInstanceUrl(salesforceAuthCredentials.getInstanceUrl());
		appAuthCredentials.setSalesforceId(salesforceAuthCredentials.getId());
		appAuthCredentials.setTokenType(salesforceAuthCredentials.getTokenType());
		appAuthCredentials.setIssuedAt(salesforceAuthCredentials.getIssuedAt());
		appAuthCredentials.setSignature(salesforceAuthCredentials.getSignature());
		try {
			authCredentialsDbService.save(appAuthCredentials);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void fetchFromTheServer() {
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
	
}
