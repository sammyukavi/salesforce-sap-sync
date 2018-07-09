package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SyncDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;
import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.util.Console;
import ke.co.blueconsulting.sianroses.util.Logger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;

class SyncHelper {
	SyncContract.View syncDashboard;
	AuthCredentialsDbService authCredentialsDbService;
	AuthDataService authDataService;
	SyncDataService syncDataService;
	SyncDbService syncDbService;
	
	void addPreloader() {
		syncDashboard.setIsBusy(true);
	}
	
	void removePreloader() {
		syncDashboard.setIsBusy(false);
	}
	
	boolean hasAccessToken() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getSalesforceAccessToken()) &&
				!StringUtils.isBlank(credentials.getSalesforceAccessToken());
	}
	
	public boolean hasCredentials() {
		AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
		return !StringUtils.isNullOrEmpty(credentials.getServerAddress()) &&
				!StringUtils.isBlank(credentials.getServerAddress()) &&
				!StringUtils.isNullOrEmpty(String.valueOf(credentials.getServerPort())) &&
				!StringUtils.isBlank(String.valueOf(credentials.getServerPort())) &&
				!StringUtils.isNullOrEmpty(credentials.getDatabaseName()) &&
				!StringUtils.isBlank(credentials.getDatabaseName()) &&
				!StringUtils.isNullOrEmpty(credentials.getDatabaseUsername()) &&
				!StringUtils.isBlank(credentials.getDatabaseUsername()) &&
				!StringUtils.isNullOrEmpty(credentials.getDatabasePassword()) &&
				!StringUtils.isBlank(credentials.getDatabasePassword()) &&
				!StringUtils.isNullOrEmpty(String.valueOf(credentials.getSyncPeriod())) &&
				!StringUtils.isBlank(String.valueOf(credentials.getSyncPeriod())) &&
				!StringUtils.isNullOrEmpty(credentials.getSyncPeriodUnit()) &&
				!StringUtils.isBlank(credentials.getSyncPeriodUnit()) &&
				!StringUtils.isNullOrEmpty(credentials.getSalesforceClientId()) &&
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
			//e.printStackTrace();
			Logger.log("Failed to store salesforce Credentials. " + e.getMessage());
		}
	}
	
	void fetchFromTheServer() {
		DataService.GetCallback<ServerResponse> getFromTheServerCallback = new DataService.GetCallback<ServerResponse>() {
			@Override
			public void onCompleted(ServerResponse serverResponse) {
				Console.log(serverResponse);
				try {
					//syncDbService.insertRecords(Customer.class, serverResponse.getCustomers());
					//syncDbService.insertRecords(CustomerContacts.class, serverResponse.getCustomerContacts());
					
					syncDbService.insertRecords(PriceList.class, serverResponse.getPriceList());
					
					//TODO This line results into an error Product_Type__c is missing in Salesforce. Check query
					
					//syncDbService.insertRecords(Product.class, serverResponse.getProducts());
					
					//syncDbService.insertRecords(ProductChild.class, serverResponse.getProductsChildren());
					//syncDbService.insertRecords(Warehouse.class, serverResponse.getWarehouses());
					
				} catch (Exception e) {
					e.printStackTrace();
					Logger.log("failed to save to MSSQL server. " + e.getMessage());
				}
			}
			
			@Override
			public void onError(Throwable t) {
				Logger.log("failed to fetch from the server. " + t.getMessage());
			}
			
			@Override
			public void always() {
			
			}
		};
		syncDataService.getFromServer(getFromTheServerCallback);
	}
	
	private void sendToTheServer() throws SQLException {
		
		DataService.GetCallback<ServerResponse> postToserverCallback = new DataService.GetCallback<ServerResponse>() {
			@Override
			public void onCompleted(ServerResponse serverResponse) {
				Console.log(serverResponse);
			}
			
			@Override
			public void onError(Throwable t) {
				//t.printStackTrace();
			}
			
			@Override
			public void always() {
			
			}
		};
		//List<Customer> customerList = syncDbService.getUnsyncedRecords(Customer.class);
		//ServerResponse serverResponse = new ServerResponse();
		//serverResponse.addData("customers", customerList);
		//syncDataService.postToServer(serverResponse, postToserverCallback);
	}
	
}
