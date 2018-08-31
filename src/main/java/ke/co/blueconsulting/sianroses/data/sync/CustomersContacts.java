package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;

public class CustomersContacts extends BaseDbDataService<SyncRestService> {
	
	/*private static final String PROCESS_NAME = "CONTACTS_SYNC";
	private static SyncDataService syncDataService;
	private static CustomerContactDbService dbService;
	private static SyncContract.View syncDashboard;
	
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		syncDataService = dataService;
		
		dbService = new CustomerContactDbService();
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<CustomerContact> customerContacts = response.getCustomerContacts();
				
				ArrayList<CustomerContact> insertedCustomers = new ArrayList<>();
				
				int contactsCount = customerContacts.size();
				
				AppLogger.logInfo("Received " + contactsCount + " contacts from Salesforce");
				
				try {
					
					if (contactsCount > 0) {
						
						customerContacts = updateCustomerContactSyncFields(customerContacts, false, false);
						
						insertedCustomers = dbService.upsertCustomerRecords(customerContacts);
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
					AppLogger.logError("An error occured while inserting the customer's contacts. " + e.getMessage());
					
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
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		syncDataService.getContacts(getFromSalesforceCallback);
	}
	
	
	private static void updateSalesforceCustomers(ArrayList<CustomerContact> customerContacts) {
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<CustomerContact> unsyncedCustomers = new ArrayList<>();
		
		try {
			
			ArrayList<Integer> customerIds = new ArrayList<>();
			
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
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<CustomerContact> customers = response.getCustomerContacts();
				
				int contactsCount = customers.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. Received " + contactsCount
						+ " contacts from Salesforce for updating");
				
				try {
					
					if (contactsCount > 0) {
						
						customers = updateCustomerContactSyncFields(customers, false, false);
						
						dbService.upsertCustomerRecords(customers);
					}
					
				} catch (SQLException e) {
					
					AppLogger.logError("An error occurred while inserting contacts. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Contacts sync complete");
					
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
		
	}*/
}
