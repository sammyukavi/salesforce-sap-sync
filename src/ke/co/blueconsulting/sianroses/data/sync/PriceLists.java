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

public class PriceLists {
	
	private static SyncDataService syncDataService;
	private static PriceListDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		syncDataService = dataService;
		dbService = new PriceListDbService();
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<PriceList> priceList = new ArrayList<>();
		
		try {
			priceList = dbService.getRecordsWithPullFromSAPCheckedTrue(PriceList.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				try {
					AppLogger.logInfo("Sync of PriceList Object Successful");
				} catch (Exception e) {
					AppLogger.logError("Failed to insert pushed priceLists from salesforce for updating. " + e.getMessage());
				}
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to push priceLists to salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				syncDashboard.setIsBusy(false);
			}
		};
		
		syncDataService.pushPriceListToSalesforce(Response.setPriceList(priceList), callback);
		
	}
}
