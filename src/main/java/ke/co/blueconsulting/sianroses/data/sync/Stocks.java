package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.StocksDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Stock;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Stocks {
	
	private final String PROCESS_NAME = "STOCKMANAGEMENT_SYNC";
	private final int resultsPerPage = 3000;
	private SyncDataService syncDataService;
	private StocksDbService dbService;
	private SyncContract.View syncDashboard;
	private long totalRecords = 0;
	private long totalPages;
	private long lastRecordId = 0;
	
	
	public Stocks(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		this.syncDashboard = syncDashboard;
		this.syncDataService = syncDataService;
		this.dbService = new StocksDbService();
	}
	
	public void sync() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		dbService.getUnsyncedCount(new DataService.GetCallback<Long>() {
			
			@Override
			public void onCompleted(Long total) {
				
				if (total > 0) {
					totalRecords = total;
					long remainder = totalRecords % resultsPerPage;
					totalPages = totalRecords / resultsPerPage;
					if (remainder != 0) {
						totalPages += 1;
					}
					fetchRecords();
				}
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("An error occurred when querying stocks count. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		});
	}
	
	private void fetchRecords() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		if (totalPages > 0) {
			
			DataService.GetCallback<ArrayList<Stock>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<Stock>>() {
				
				@Override
				public void onCompleted(ArrayList<Stock> stock) {
					if (!stock.isEmpty()) {
						totalPages = totalPages - 1;
						lastRecordId = stock.get(stock.size() - 1).getAutoId();
						pushToSalesforce(stock);
					}
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
			
			dbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback, resultsPerPage, lastRecordId);
		}
	}
	
	private void pushToSalesforce(ArrayList<Stock> stock) {
		
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
	
	
	private void upsertSap(ArrayList<Stock> stock) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		stock = (ArrayList<Stock>) updateSyncFields(stock, false, false);
		
		DataService.GetCallback<ArrayList<Stock>> upsertRecordsCallback = new DataService.GetCallback<ArrayList<Stock>>() {
			
			@Override
			public void onCompleted(ArrayList<Stock> stock) {
				
				if (totalPages > 0) {
					fetchRecords();
				} else {
					AppLogger.logInfo("Stock Management sync is complete");
				}
				
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
