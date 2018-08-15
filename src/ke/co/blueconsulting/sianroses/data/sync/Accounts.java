package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.SAPDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.Console;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateCustomerPushToSAPFields;

public class Accounts {
	
	private static SyncDataService syncDataService;
	private static SAPDbService sapDbService;
	private static SyncContract.View syncDashboard;
	
	
	public static <T> void sync(SyncContract.View view, SyncDataService dataService, SAPDbService dbService) {
		
		syncDashboard = view;
		syncDataService = dataService;
		sapDbService = dbService;
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Customer> customers = response.getCustomers();
				
				ArrayList<Customer> insertedCustomers = new ArrayList<>();
				
				if (customers.size() > 0) {
					
					customers = updateCustomerPushToSAPFields(customers);
					
					try {
						insertedCustomers = sapDbService.insertCustomerRecords(customers);
					} catch (SQLException e) {
						e.printStackTrace();
						AppLogger.logError(e.getMessage() + "\n" + e.getCause());
						
					}
				} else {
					AppLogger.logInfo("Received no customers from Salesforce");
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
		
		syncDataService.getUserAccounts(getFromSalesforceCallback);
	}
	
	
	private static void updateSalesforceCustomers(ArrayList<Customer> customers) {
		
		syncDashboard.setIsBusy(true);
		//get customers that exist in the SAP but not in Salesforce
		ArrayList<Customer> unsyncedCustomers = new ArrayList<>();
		
		try {
			ArrayList<Long> customerIds = new ArrayList<>();
			for (Customer customer : customers) {
				customerIds.add(customer.getAutoId());
			}
			unsyncedCustomers = updateCustomerPushToSAPFields(sapDbService.getUnsyncedCustomers(customerIds));
		} catch (SQLException e) {
			e.printStackTrace();
			AppLogger.logError("Error fetching unsynced customers. " + e.getMessage() + "\n" + e.getCause());
		}
		
		customers.addAll(unsyncedCustomers);
		
		if (customers.isEmpty()) {
			AppLogger.logInfo("Found 0 customers that need to be pushed to Salesforce");
		} else {
			
			DataService.GetCallback<Response> pushToSalesforce = new DataService.GetCallback<Response>() {
				@Override
				public void onCompleted(Response response) {
					Console.logToJson(response.getCustomers());
				}
				
				@Override
				public void onError(Throwable t) {
				
				}
				
				@Override
				public void always() {
					syncDashboard.setIsBusy(false);
				}
			};
			
			syncDataService.pushCustomersToSalsesforce(Response.setCustomers(customers), pushToSalesforce);
		}
		
	}
}
