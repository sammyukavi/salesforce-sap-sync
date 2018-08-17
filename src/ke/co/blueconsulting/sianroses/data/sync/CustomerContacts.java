package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.CustomerContactDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateCustomerContactSyncFields;

public class CustomerContacts {
	
	private static SyncDataService syncDataService;
	private static CustomerContactDbService dbService;
	private static SyncContract.View syncDashboard;
	
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		syncDataService = dataService;
		dbService = new CustomerContactDbService();
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<CustomerContact> customerContacts = response.getCustomerContacts();
				
				ArrayList<CustomerContact> insertedCustomers = new ArrayList<>();
				
				int customersCount = customerContacts.size();
				
				AppLogger.logInfo("Received " + customersCount + " contacts from Salesforce");
				
				if (customersCount > 0) {
					
					customerContacts = updateCustomerContactSyncFields(customerContacts, false, false);
					
					try {
						insertedCustomers = dbService.upsertCustomerRecords(customerContacts);
					} catch (SQLException e) {
						AppLogger.logError(e.getMessage());
					}
				}
				
				updateSalesforceCustomers(insertedCustomers);
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to fetch from the server. " + t.getMessage());
				//TODO show the error here if the Dashboard UI is hidden
			}
			
			@Override
			public void always() {
				syncDashboard.setIsBusy(false);
			}
		};
		
		syncDataService.getContacts(getFromSalesforceCallback);
	}
	
	
	private static void updateSalesforceCustomers(ArrayList<CustomerContact> customerContacts) {
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<CustomerContact> unsyncedCustomers = new ArrayList<>();
		
		try {
			
			ArrayList<Long> customerIds = new ArrayList<>();
			
			for (CustomerContact customerContact : customerContacts) {
				customerIds.add(customerContact.getAutoId());
			}
			
			unsyncedCustomers = updateCustomerContactSyncFields(
					dbService.getUnsyncedCustomerContacts(customerIds), false, false);
			
		} catch (SQLException e) {
			AppLogger.logError(e.getMessage());
		}
		
		customerContacts.addAll(unsyncedCustomers);
		
		int customersCount = customerContacts.size();
		
		AppLogger.logInfo("Found " + customersCount + " contacts that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		if (customersCount > 0) {
			
			DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
				@Override
				public void onCompleted(Response response) {
					
					ArrayList<CustomerContact> customers = response.getCustomerContacts();
					
					int customersCount = customers.size();
					
					AppLogger.logInfo("Push To Salesforce Successful. " +
							"Received " + customersCount + " contacts from Salesforce for updating");
					
					if (customersCount > 0) {
						
						customers = updateCustomerContactSyncFields(customers, false, false);
						
						try {
							dbService.upsertCustomerRecords(customers);
							AppLogger.logInfo("Contacts sync complete");
						} catch (SQLException e) {
							e.printStackTrace();
							AppLogger.logError(e.getMessage());
						}
					}
				}
				
				@Override
				public void onError(Throwable e) {
					AppLogger.logError("Error pushing contacts to Salesforce. " + e.getMessage());
				}
				
				@Override
				public void always() {
					syncDashboard.setIsBusy(false);
				}
			};
			
			syncDataService.pushCustomersContactsToSalesforce(Response.setCustomerContacts(customerContacts), pushToSalesforceCallback);
		} else {
			AppLogger.logInfo("Contacts sync complete");
		}
		
	}
}
