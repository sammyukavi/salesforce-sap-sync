package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.CustomerDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Customers {
	
	private static final String PROCESS_NAME = "CUSTOMERS_SYNC";
	private static SyncDataService syncDataService;
	private static CustomerDbService dbService;
	private static SyncContract.View syncDashboard;
	
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		syncDataService = dataService;
		
		dbService = new CustomerDbService();
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Customer> customers = response.getCustomers();
				
				upsertSap(customers);
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("An error occurred when fetching customer records from Salesforce. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
				getUnsyncedSalesforceCustomers();
			}
		};
		
		syncDataService.getCustomers(getFromSalesforceCallback);
	}
	
	
	private static void getUnsyncedSalesforceCustomers() {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<ArrayList<Customer>> getUnsyncedCustomersCallback = new DataService.GetCallback<ArrayList<Customer>>() {
			
			@Override
			public void onCompleted(ArrayList<Customer> customerArrayList) {
				pushToSalesforce(customerArrayList);
			}
			
			@Override
			public void onError(Throwable e) {
				AppLogger.logError("An error occurred when fetching unsynced customer records from Sap. " + e.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.getUnsynced(getUnsyncedCustomersCallback);
		
		
	}
	
	private static void pushToSalesforce(ArrayList<Customer> customers) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		customers = (ArrayList<Customer>) updateSyncFields(customers, false, false);
		
		int customersCount = customers.size();
		
		AppLogger.logInfo("Found " + customersCount + " customers that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		if (customersCount > 0) {
			
			DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
				
				@Override
				public void onCompleted(Response response) {
					
					ArrayList<Customer> customers = response.getCustomers();
					
					int customersCount = customers.size();
					
					AppLogger.logInfo("Push To Salesforce Successful. " +
							"Received " + customersCount + " customers from Salesforce for updating");
					
					customers = (ArrayList<Customer>) updateSyncFields(customers, false, false);
					
					upsertSap(customers);
					
				}
				
				@Override
				public void onError(Throwable e) {
					
					AppLogger.logError("An error occurred when pushing customer records to Salesforce. " + e.getMessage());
				}
				
				@Override
				public void always() {
					
					syncDashboard.setIsBusy(false);
					
					syncDataService.removeFromProcessStack(PROCESS_NAME);
				}
			};
			
			syncDataService.pushCustomersToSalesforce(Response.setCustomers(customers), pushToSalesforceCallback);
			
		} else {
			AppLogger.logInfo("Customers sync complete");
		}
	}
	
	private static void upsertSap(ArrayList<Customer> customers) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		customers = (ArrayList<Customer>) updateSyncFields(customers, false, false);
		;
		
		DataService.GetCallback<ArrayList<Customer>> upsertCustomersCallback = new DataService.GetCallback<ArrayList<Customer>>() {
			
			@Override
			public void onCompleted(ArrayList<Customer> response) {
				
				AppLogger.logInfo("Customers sync complete");
				
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("An error occurred when upserting customer records. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.upsertRecords(customers, upsertCustomersCallback);
	}
}
