package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.SAPDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateCustomerPullFromSAPFields;
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
				
				int customersCount = customers.size();
				
				AppLogger.logInfo("Received " + customersCount + " customers from Salesforce");
				
				if (customersCount > 0) {
					
					customers = updateCustomerPushToSAPFields(customers);
					
					try {
						insertedCustomers = sapDbService.insertCustomerRecords(customers);
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
		
		syncDataService.getUserAccounts(getFromSalesforceCallback);
	}
	
	
	private static void updateSalesforceCustomers(ArrayList<Customer> customers) {
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<Customer> unsyncedCustomers = new ArrayList<>();
		
		try {
			
			ArrayList<Long> customerIds = new ArrayList<>();
			
			for (Customer customer : customers) {
				customerIds.add(customer.getAutoId());
			}
			
			unsyncedCustomers = updateCustomerPushToSAPFields(sapDbService.getUnsyncedCustomers(customerIds));
			
		} catch (SQLException e) {
			AppLogger.logError(e.getMessage());
		}
		
		customers.addAll(unsyncedCustomers);
		
		int customersCount = customers.size();
		
		AppLogger.logInfo("Found " + customersCount + " customers that need to be pushed to Salesforce." + (customersCount > 0 ? "Attempting to push." : ""));
		
		if (customersCount > 0) {
			
			DataService.GetCallback<Response> pushToSalesforce = new DataService.GetCallback<Response>() {
				@Override
				public void onCompleted(Response response) {
					
					ArrayList<Customer> customers = response.getCustomers();
					
					int customersCount = customers.size();
					
					AppLogger.logInfo("Push To Salesforce Successful. " +
							"Received " + customersCount + " customers from Salesforce for updating");
					
					if (customersCount > 0) {
						
						customers = updateCustomerPullFromSAPFields(customers);
						
						try {
							sapDbService.updateCustomerRecords(customers);
							AppLogger.logInfo("Sync Complete");
						} catch (SQLException e) {
							AppLogger.logError(e.getMessage());
						}
					}
				}
				
				@Override
				public void onError(Throwable e) {
					AppLogger.logError("Error pushing customers to Salesforce. " + e.getMessage());
				}
				
				@Override
				public void always() {
					syncDashboard.setIsBusy(false);
				}
			};
			
			syncDataService.pushCustomersToSalsesforce(Response.setCustomers(customers), pushToSalesforce);
		} else {
			AppLogger.logInfo("Sync Complete");
		}
		
	}
}
