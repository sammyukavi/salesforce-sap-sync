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

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updatePriceListSyncFields;

public class Warehouses {
	
	private static PriceListDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		syncDashboard = view;
		dbService = new PriceListDbService();
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<PriceList> priceLists = new ArrayList<>();
		
		try {
			priceLists = dbService.getRecordsWithPullFromSAPCheckedTrue(PriceList.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<PriceList> priceLists = response.getPriceList();
				
				int listsCount = priceLists.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + listsCount + " price lists from Salesforce for updating");
				
				if (listsCount > 0) {
					
					priceLists = updatePriceListSyncFields(priceLists, false, false);
					
					try {
						dbService.upsertRecords(priceLists);
						AppLogger.logInfo("Price lists sync complete");
					} catch (SQLException e) {
						e.printStackTrace();
						AppLogger.logError(e.getMessage());
					}
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
		
		dataService.pushPriceListToSalesforce(Response.setPriceList(priceLists), callback);
		
	}
}
