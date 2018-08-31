package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.CustomerDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
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
				
				ArrayList<Customer> insertedCustomers = new ArrayList<>();
				
				int customersCount = customers.size();
				
				AppLogger.logInfo("Received " + customersCount + " customers from Salesforce");
				
				try {
					if (customersCount > 0) {
						
						customers = (ArrayList<Customer>) updateSyncFields(customers, false, false);
						
						insertedCustomers = dbService.upsertCustomerRecords(customers);
					}
				} catch (SQLException e) {
					AppLogger.logError("An error occurred when upserting customer records. " + e.getMessage());
					
				} finally {
					
					updateSalesforceCustomers(insertedCustomers);
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to fetch from the server. " + t.getMessage());
				//TODO show the error here if the Dashboard UI is hidden
			}
			
			@Override
			public void always() {
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		syncDataService.getCustomers(getFromSalesforceCallback);
	}
	
	
	private static void updateSalesforceCustomers(ArrayList<Customer> customers) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<Customer> unsyncedCustomers = new ArrayList<>();
		
		try {
			
			ArrayList<Integer> customerIds = new ArrayList<>();
			
			for (Customer customer : customers) {
				customerIds.add(customer.getAutoId());
			}
			
			unsyncedCustomers = (ArrayList<Customer>) updateSyncFields(dbService.getUnsyncedCustomers(customerIds), false, false);
			
		} catch (SQLException e) {
			
			AppLogger.logError("An error occurred when querying unsynced customers. " + e.getMessage());
			
		}
		
		customers.addAll(unsyncedCustomers);
		
		int customersCount = customers.size();
		
		AppLogger.logInfo("Found " + customersCount + " customers that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		
		DataService.GetCallback<Response> pushToSalesforce = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Customer> customers = response.getCustomers();
				
				int customersCount = customers.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + customersCount + " customers from Salesforce for updating");
				
				try {
					
					if (customersCount > 0) {
						
						customers = (ArrayList<Customer>) updateSyncFields(customers, false, false);
						
						dbService.upsertCustomerRecords(customers);
					}
					
				} catch (SQLException e) {
					
					AppLogger.logError("An error occurred when upserting customer records. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Customers sync complete");
					
				}
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("Error pushing customers to Salesforce. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				syncDashboard.setIsBusy(false);
			}
		};
		
		syncDataService.pushCustomersToSalesforce(Response.setCustomers(customers), pushToSalesforce);
		
	}
}
