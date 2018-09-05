package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.StockDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Stock;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Stocks {
	
	private static final String PROCESS_NAME = "STOCKMANAGEMENT_SYNC";
	private static SyncDataService syncDataService;
	private static StockDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new StockDbService();
		
		syncDataService = dataService;
		
		syncDashboard.setIsBusy(true);
		
		dataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<ArrayList<Stock>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<Stock>>() {
			
			@Override
			public void onCompleted(ArrayList<Stock> stock) {
				
				pushToSaleforce(stock);
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occurred when querying stock. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback, 1000L);
		
	}
	
	private static void pushToSaleforce(ArrayList<Stock> stock) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> pushStockToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Stock> stock = response.getStock();
				
				int stockCount = stock.size();
				
				AppLogger.logInfo("Push to salesforce successful. " + "Received " + stockCount + " stock from Salesforce for updating");
				
				upsertSap(stock);
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to push stock to Salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		syncDataService.pushStockToSalesforce(Response.setStock(stock), pushStockToSalesforceCallback);
		
	}
	
	private static void upsertSap(ArrayList<Stock> stock) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		stock = (ArrayList<Stock>) updateSyncFields(stock, false, false);
		
		DataService.GetCallback<ArrayList<Stock>> upsertRecordsCallback = new DataService.GetCallback<ArrayList<Stock>>() {
			
			@Override
			public void onCompleted(ArrayList<Stock> response) {
				
				AppLogger.logInfo("StockManagements sync is complete");
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("An error occurred when upserting stock. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.upsertRecords(stock, upsertRecordsCallback);
		
	}
}
