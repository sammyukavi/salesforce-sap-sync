package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.PriceListDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class PriceLists {
	
	private static final String PROCESS_NAME = "PRICE_LISTS_SYNC";
	private static PriceListDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new PriceListDbService();
		
		syncDashboard.setIsBusy(true);
		
		dataService.addToProcessStack(PROCESS_NAME);
		
		ArrayList<PriceList> priceLists = new ArrayList<>();
		
		try {
			
			priceLists = dbService.getRecordsWithPullFromSapCheckedTrue(PriceList.class);
			
		} catch (SQLException e) {
			
			AppLogger.logError("An error occurred when querying price lists. " + e.getMessage());
		}
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<PriceList> priceLists = response.getPriceList();
				
				int listsCount = priceLists.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + listsCount + " price lists from Salesforce for updating");
				
				try {
					
					if (listsCount > 0) {
						
						priceLists = (ArrayList<PriceList>) updateSyncFields(priceLists, false, false);
						
						dbService.upsertRecords(priceLists);
					}
					
				} catch (SQLException e) {
					
					AppLogger.logError("An error occured when upserting price lists. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Price lists sync complete");
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Failed to push price lists to salesforce. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		dataService.pushPriceListToSalesforce(Response.setPriceList(priceLists), pushToSalesforceCallback);
		
	}
}
