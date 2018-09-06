package ke.co.blueconsulting.sianroses.data.sync;


import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.CustomerContactDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateContactSyncFields;


public class CustomerContacts {
	
	private final String PROCESS_NAME = "CONTACTS_SYNC";
	private SyncDataService syncDataService;
	private CustomerContactDbService dbService;
	private SyncContract.View syncDashboard;
	
	public CustomerContacts(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		
		this.syncDashboard = syncDashboard;
		
		this.syncDataService = syncDataService;
		
		this.dbService = new CustomerContactDbService();
	}
	
	
	public void sync() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<CustomerContact> customers = response.getCustomerContacts();
				
				upsertSap(customers);
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("An error occurred when fetching customers contacts records from Salesforce. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
				getUnsyncedSalesforceCustomerContact();
			}
		};
		
		syncDataService.getContacts(getFromSalesforceCallback);
	}
	
	
	private void getUnsyncedSalesforceCustomerContact() {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<ArrayList<CustomerContact>> getUnsyncedCustomerContactCallback = new DataService.GetCallback<ArrayList<CustomerContact>>() {
			
			@Override
			public void onCompleted(ArrayList<CustomerContact> customerArrayList) {
				
				ArrayList<CustomerContact> unsyncedCustomerContact = updateContactSyncFields(customerArrayList, false, false);
				
				updateSalesforceCustomerContact(unsyncedCustomerContact);
				
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("An error occurred when fetching unsynced customers contacts records from Sap. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.getUnsynced(getUnsyncedCustomerContactCallback);
		
		
	}
	
	private void updateSalesforceCustomerContact(ArrayList<CustomerContact> customers) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		int customersCount = customers.size();
		
		AppLogger.logInfo("Found " + customersCount + " customers contacts that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		if (customersCount > 0) {
			
			DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
				
				@Override
				public void onCompleted(Response response) {
					
					ArrayList<CustomerContact> customers = response.getCustomerContacts();
					
					int customersCount = customers.size();
					
					AppLogger.logInfo("Push To Salesforce Successful. Received " + customersCount + " customers' contacts from Salesforce for updating");
					
					upsertSap(customers);
					
				}
				
				@Override
				public void onError(Throwable e) {
					
					AppLogger.logError("An error occurred when pushing customers' contacts records to Salesforce. " + e.getMessage());
				}
				
				@Override
				public void always() {
					
					syncDashboard.setIsBusy(false);
					
					syncDataService.removeFromProcessStack(PROCESS_NAME);
				}
			};
			
			syncDataService.pushCustomersContactsToSalesforce(Response.setCustomerContacts(customers), pushToSalesforceCallback);
			
		} else {
			AppLogger.logInfo("Customers' Contacts sync complete");
		}
	}
	
	private void upsertSap(ArrayList<CustomerContact> contacts) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		contacts = updateContactSyncFields(contacts, false, false);
		
		DataService.GetCallback<ArrayList<CustomerContact>> upsertCustomerContactCallback = new DataService.GetCallback<ArrayList<CustomerContact>>() {
			
			@Override
			public void onCompleted(ArrayList<CustomerContact> response) {
				AppLogger.logInfo("Customers contacts sync complete");
			}
			
			@Override
			public void onError(Throwable e) {
				AppLogger.logError("An error occurred when upserting customers' contacts records. " + e.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.upsertRecords(contacts, upsertCustomerContactCallback);
	}
}
