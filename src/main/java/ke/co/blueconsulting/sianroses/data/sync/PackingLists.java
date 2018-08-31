package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.PackingListDbService;
import ke.co.blueconsulting.sianroses.data.db.PackingListItemDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingList;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingListItem;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class PackingLists {
	
	private static final String PROCESS_NAME = "PACKING_LISTS_SYNC";
	private static PackingListDbService packingListDbService;
	private static PackingListItemDbService packingListItemDbService;
	private static SyncContract.View syncDashboard;
	private static SyncDataService syncDataService;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		packingListDbService = new PackingListDbService();
		
		packingListItemDbService = new PackingListItemDbService();
		
		syncDataService = dataService;
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<PackingList> packingLists = response.getPackingLists();
				
				ArrayList<PackingList> insertedPackingLists = new ArrayList<>();
				
				AppLogger.logInfo("Received " + packingLists.size() + " packing lists from Salesforce");
				
				try {
					
					insertedPackingLists = performUpserts(insertedPackingLists);
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
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
	
	private static ArrayList<PackingList> performUpserts(ArrayList<PackingList> packingLists) throws SQLException {
		
		updateSyncFields(packingLists, false, false);
		
		ArrayList<PackingList> insertedPackingLists = packingListDbService.upsertRecords(packingLists);
		
		for (PackingList packingList : insertedPackingLists) {
			
			ArrayList<PackingListItem> records = packingList.getPackingListItems().getRecords();
			
			if (records != null) {
				packingListItemDbService.upsertRecords(records, packingList.getAutoId());
			}
			
		}
		
		return insertedPackingLists;
	}
	
	private static void updateSalesforcePackingList(ArrayList<PackingList> insertedPackingLists) {
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<PackingList> unsyncedPackingLists = new ArrayList<>();
		
		try {
			
			ArrayList<Integer> packingListsIds = new ArrayList<>();
			
			for (PackingList packingList : insertedPackingLists) {
				packingListsIds.add(packingList.getAutoId());
			}
			
			unsyncedPackingLists = (ArrayList<PackingList>) updateSyncFields(packingListDbService.getUnsyncedPackingLists(packingListsIds), false, false);
			
		} catch (SQLException e) {
			AppLogger.logError(e.getMessage());
		}
		
		insertedPackingLists.addAll(unsyncedPackingLists);
		
		ArrayList<PackingList> packingListToPush = new ArrayList<>();
		
		for (PackingList packingList : insertedPackingLists) {
			packingList.setPackingListItems(null);
			packingList.setPostingDate(null);
			packingListToPush.add(packingList);
		}
		
		int customersCount = insertedPackingLists.size();
		
		AppLogger.logInfo("Found " + customersCount + " packing lists that need to be pushed to Salesforce. " + (customersCount > 0 ? "Attempting to push." : ""));
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<PackingList> packingLists = response.getPackingLists();
				
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + packingLists.size() + " packing lists from Salesforce for updating");
				
				try {
					
					performUpserts(packingLists);
					
				} catch (SQLException e) {
					
					e.printStackTrace();
					
					AppLogger.logError("An error occured when upserting packing lists. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Packing Lists sync complete");
					
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
		
		syncDataService.pushPackingListsToSalesforce(Response.setPackingLists(packingListToPush), pushToSalesforceCallback);
		
	}
	
	
}
