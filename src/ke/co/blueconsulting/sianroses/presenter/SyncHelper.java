package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SAPDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.FetchDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncContactsDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncCustomersDataService;
import ke.co.blueconsulting.sianroses.model.app.*;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateCustomerContactsPushToSAPFields;
import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateCustomerPushToSAPFields;

class SyncHelper {

	SyncContract.View syncDashboard;
	AuthCredentialsDbService authCredentialsDbService;
	SAPDbService sapDbService;

	SyncHelper() throws SQLException, ClassNotFoundException {
		this.authCredentialsDbService = new AuthCredentialsDbService();
		this.sapDbService = new SAPDbService();
	}

	void addPreloader() {
		syncDashboard.setIsBusy(true);
	}

	void removePreloader() {
		syncDashboard.setIsBusy(false);
	}

	boolean hasAccessToken() {
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

	AuthDataService getAuthService(){
		RestServiceBuilder.switchToSalesforceAuthUrl();
		return new AuthDataService();
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
			AppLogger.logWarning("Failed to store salesforce Credentials. " + e.getMessage());
		}
	}
	
	private void insertCustomersToSAP(ArrayList<Customer> customers) throws SQLException {				

		customers = updateCustomerPushToSAPFields(customers);					

		ArrayList<Customer> insertedAndUpdatedCustomers = sapDbService.insertRecords(Customer.class, customers);

		updateSalesforceAccounts(insertedAndUpdatedCustomers);
	}			

	private void updateSalesforceAccounts(ArrayList<Customer> customers) throws SQLException {

		//get customers that exist in the SAP but not in Salesforce
		List<Customer> unsyncedContacts = updateCustomerPushToSAPFields(sapDbService.getRecordsWithoutSalesforceId(Customer.class));

		//Add the unsynced customers to the salesforce customers
		customers.addAll(unsyncedContacts);

		SyncCustomersDataService syncCustomersDataService = new SyncCustomersDataService();

		DataService.GetCallback<PushCustomer> callback = new DataService.GetCallback<PushCustomer>() {
			@Override
			public void onCompleted(PushCustomer customers) {
				try {
					sapDbService.insertRecords(Customer.class, customers.getAccounts());
					AppLogger.logInfo("Sync of Customer Object Successful");
				} catch (SQLException e) {
					AppLogger.logWarning("Failed to insert pushed customers from salesforce for updating. " + e.getMessage());
				}
			}

			@Override
			public void onError(Throwable t) {
				AppLogger.logWarning("Failed to push customers to salesforce. " + t.getMessage());
			}

			@Override
			public void always() {

			}
		};

		syncCustomersDataService.pushCustomersToServer(new PushCustomer(customers), callback);

	}
	
	private void insertCustomerContactsToSAP(ArrayList<CustomerContacts> customerContacts) throws SQLException {
		
		customerContacts =  updateCustomerContactsPushToSAPFields(customerContacts);

		ArrayList<CustomerContacts> insertedAndUpdatedCustomers = sapDbService.insertRecords(CustomerContacts.class, customerContacts);

		updateSalesforceContacts(insertedAndUpdatedCustomers);
		
	}
	
	private void updateSalesforceContacts(ArrayList<CustomerContacts> customerContacts) throws SQLException {
		
		//get customers that exist in the SAP but not in Salesforce
		List<CustomerContacts> unsyncedContacts = updateCustomerContactsPushToSAPFields(
				sapDbService.getRecordsWithoutSalesforceId(CustomerContacts.class,"CONTACTID")
		);
		
		//Add the contacts to the updated contacts
		customerContacts.addAll(unsyncedContacts);
		
		SyncContactsDataService contactsDataService = new SyncContactsDataService();
		
		
		DataService.GetCallback<PushCustomerContacts> callback = new DataService.GetCallback<PushCustomerContacts>() {
			@Override
			public void onCompleted(PushCustomerContacts customerContacts) {
				try {
					sapDbService.insertRecords(CustomerContacts.class, customerContacts.getContacts());
					AppLogger.logInfo("Sync of CustomerContacts Object Successful");
				} catch (SQLException e) {
					AppLogger.logWarning("Failed to insert pushed customers' contacts from salesforce for updating. " + e.getMessage());
				}
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logWarning("Failed to push customers' contacts to salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
			
			}
		};
		contactsDataService.pushContactsToServer(new PushCustomerContacts(customerContacts), callback);
	}
	
	
	void fetchFromTheServer() {

		DataService.GetCallback<Result> getFromTheServerCallback = new DataService.GetCallback<Result>() {
			@Override
			public void onCompleted(Result receivedRecords) {
				try {
					//insertCustomersToSAP(receivedRecords.getCustomers());
					insertCustomerContactsToSAP(receivedRecords.getCustomerContacts());
				} catch (Exception e) {					
					AppLogger.logWarning("failed to insert received records into to MSSQL server. " + e.getMessage());
				}
			}

			@Override
			public void onError(Throwable t) {
				AppLogger.logWarning("failed to fetch from the server. " + t.getMessage());
			}

			@Override
			public void always() {

			}
		};
		
		
		RestServiceBuilder.switchToSalesforceApiBaseUrl();
		FetchDataService fetchDataService = new FetchDataService();
		fetchDataService.getFromServer(getFromTheServerCallback);
	}

}
