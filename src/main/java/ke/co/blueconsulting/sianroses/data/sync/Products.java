package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.ProductDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Products {
	
	private static final String PROCESS_NAME = "PRODUCTS_SYNC";
	private static ProductDbService productDbService;
	private static SyncContract.View syncDashboard;
	private static SyncDataService syncDataService;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		productDbService = new ProductDbService();
		
		syncDataService = dataService;
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		
		DataService.GetCallback<ArrayList<Product>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<Product>>() {
			
			@Override
			public void onCompleted(ArrayList<Product> products) {
				
				pushToSalesforce(products);
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("An error occurred when querying products. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		productDbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback);
		
	}
	
	private static void pushToSalesforce(ArrayList<Product> products) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		
		DataService.GetCallback<Response> pushToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Product> products = response.getProducts();
				
				int productsCount = products.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " + "Received " + productsCount + " products from Salesforce for updating");
				
				upsertSap(products);
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to push products to salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		syncDataService.pushProductsToSalesforce(Response.setProducts(products), pushToSalesforceCallback);
		
	}
	
	private static void upsertSap(ArrayList<Product> products) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		products = (ArrayList<Product>) updateSyncFields(products, false, false);
		
		DataService.GetCallback<ArrayList<Product>> upsertProductsCallback = new DataService.GetCallback<ArrayList<Product>>() {
			
			@Override
			public void onCompleted(ArrayList<Product> returnedProducts) {
				
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
		
		productDbService.upsertRecords(products, upsertProductsCallback);
	}
	
}
