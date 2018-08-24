package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.PackingListDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.ArInvoice;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class PackingLists {
	
	private static PackingListDbService dbService;
	
	private static SyncContract.View syncDashboard;
	
	private static SyncDataService syncDataService;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new PackingListDbService();
		
		syncDataService = dataService;
		
		syncDashboard.setIsBusy(true);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<ArInvoice> packingLists = response.getPackingLists();
				
				ArrayList<ArInvoice> insertedPackingLists = new ArrayList<>();
				
				int packingListsCount = packingLists.size();
				
				AppLogger.logInfo("Received " + packingListsCount + " packing lists from Salesforce");
				
				try {
					if (packingListsCount > 0) {
						
						updateSyncFields(packingLists, false, false);
						
						insertedPackingLists = dbService.upsertRecords(packingLists);
						
					}
				} catch (SQLException e) {
					
					AppLogger.logError("An error occured when upserting packing lists. " + e.getMessage());
					
				} finally {
					
					updateSalesforcePackingList(insertedPackingLists);
					
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Error fetching packing lists from Salesforce. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		syncDataService.getPackingLists(getFromSalesforceCallback);
		
	}
	
	private static void updateSalesforcePackingList(ArrayList<ArInvoice> insertedPackingLists) {
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<ArInvoice> unsyncedPackingLists = new ArrayList<>();
		
		try {
			
			ArrayList<Integer> packingListsIds = new ArrayList<>();
			
			for (ArInvoice customer : insertedPackingLists) {
				packingListsIds.add(customer.getAutoId());
			}
			
			unsyncedPackingLists = (ArrayList<ArInvoice>) updateSyncFields(dbService.getUnsyncedPackingLists(packingListsIds), false, false);
			
		} catch (SQLException e) {
			AppLogger.logError(e.getMessage());
		}
		
		insertedPackingLists.addAll(unsyncedPackingLists);
		
		int customersCount = insertedPackingLists.size();
		
		AppLogger.logInfo("Found " + customersCount + " packing lists that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<ArInvoice> packingLists = response.getPackingLists();
				
				int customersCount = packingLists.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + customersCount + " packing lists from Salesforce for updating");
				
				try {
					if (customersCount > 0) {
						
						packingLists = (ArrayList<ArInvoice>) updateSyncFields(packingLists, false, false);
						
						dbService.upsertRecords(packingLists);
						
					}
					
				} catch (SQLException e) {
					
					AppLogger.logError("An error occured when upserting packing lists. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Customers sync complete");
					
				}
			}
			
			@Override
			public void onError(Throwable e) {
				
				AppLogger.logError("Error pushing packing lists to Salesforce. " + e.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		syncDataService.pushPackingListsToSalesforce(Response.setPackingLists(insertedPackingLists), pushToSalesforceCallback);
		
	}
	
	
}
