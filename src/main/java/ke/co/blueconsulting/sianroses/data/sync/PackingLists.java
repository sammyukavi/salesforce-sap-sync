package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.PackingListDbService;
import ke.co.blueconsulting.sianroses.data.impl.db.PackingListItemDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingList;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingListItem;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;


public class PackingLists {
	
	private final String PROCESS_NAME = "PACKING_LISTS_SYNC";
	private PackingListDbService packingListDbService;
	private PackingListItemDbService packingListItemDbService;
	private SyncContract.View syncDashboard;
	private SyncDataService syncDataService;
	
	public PackingLists(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		
		this.syncDashboard = syncDashboard;
		
		this.syncDataService = syncDataService;
		
		packingListDbService = new PackingListDbService();
		
		packingListItemDbService = new PackingListItemDbService();
		
	}
	
	private void upsertPackinglistItemsInSap(ArrayList<PackingListItem> records, int autoId) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<ArrayList<PackingListItem>> upsertRecordsCallback = new DataService.GetCallback<ArrayList<PackingListItem>>() {
			
			@Override
			public void onCompleted(ArrayList<PackingListItem> response) {
			
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occured when upserting packing list items. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		packingListItemDbService.upsertRecords(records, autoId, upsertRecordsCallback);
		
	}
	
	public void sync() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> getFromSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<PackingList> packingLists = response.getPackingLists();
				
				AppLogger.logInfo("Received " + packingLists.size() + " packing lists from Salesforce");
				
				upsertPackinglistsInSap(packingLists);
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Error fetching packing lists from Salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		syncDataService.getPackingLists(getFromSalesforceCallback);
		
	}
	
	private void upsertPackinglistsInSap(ArrayList<PackingList> packingLists) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		packingLists = (ArrayList<PackingList>) updateSyncFields(packingLists, false, false);
		
		DataService.GetCallback<ArrayList<PackingList>> upsertRecordsCallback = new DataService.GetCallback<ArrayList<PackingList>>() {
			
			@Override
			public void onCompleted(ArrayList<PackingList> upsertedPackingLists) {
				
				for (PackingList packingList : upsertedPackingLists) {
					
					ArrayList<PackingListItem> records = packingList.getPackingListItems().getRecords();
					
					if (records != null) {
						upsertPackinglistItemsInSap(records, packingList.getAutoId());
					}
					
				}
				
				updateSalesforcePackingList();
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occured when upserting packing lists. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		packingListDbService.upsertRecords(packingLists, upsertRecordsCallback);
		
	}
	
	private void updateSalesforcePackingList() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<ArrayList<PackingList>> getUnsyncedCallback = new DataService.GetCallback<ArrayList<PackingList>>() {
			
			@Override
			public void onCompleted(ArrayList<PackingList> response) {
				
				AppLogger.logInfo("Packing Lists sync complete");
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Error pushing packing lists to Salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		packingListDbService.getUnsynced(getUnsyncedCallback);
		
	}
	
}
