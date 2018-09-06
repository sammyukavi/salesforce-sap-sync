package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.PriceListDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class PriceLists {
	
	private final String PROCESS_NAME = "PRICE_LISTS_SYNC";
	private PriceListDbService dbService;
	private SyncContract.View syncDashboard;
	private SyncDataService syncDataService;
	
	public PriceLists(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		
		this.syncDashboard = syncDashboard;
		
		this.syncDataService = syncDataService;
		
		this.dbService = new PriceListDbService();
	}
	
	public void sync() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<ArrayList<PriceList>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<PriceList>>() {
			
			@Override
			public void onCompleted(ArrayList<PriceList> priceLists) {
				
				pushToSalesforce(priceLists);
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occurred when querying price lists. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		dbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback);
		
	}
	
	private void pushToSalesforce(ArrayList<PriceList> priceLists) {
		
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				
				ArrayList<PriceList> priceLists = response.getPriceList();
				
				int listsCount = priceLists.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + listsCount + " price lists from Salesforce for updating");
				
			}
			
			@Override
			public void onError(Throwable t) {
			
			}
			
			@Override
			public void always() {
			
			}
		};
		
		syncDataService.pushPriceListToSalesforce(Response.setPriceList(priceLists), pushToSalesforceCallback);
		
	}
	
	
	private void upsertSap(ArrayList<PriceList> priceLists) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		priceLists = (ArrayList<PriceList>) updateSyncFields(priceLists, false, false);
		
		DataService.GetCallback<ArrayList<PriceList>> upsertProductsCallback = new DataService.GetCallback<ArrayList<PriceList>>() {
			
			@Override
			public void onCompleted(ArrayList<PriceList> returnedProducts) {
				
				AppLogger.logInfo("Products sync complete");
				
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
		
		dbService.upsertRecords(priceLists, upsertProductsCallback);
	}
	
}
