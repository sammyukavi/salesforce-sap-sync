package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.ProductDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Products {
	
	private static final String PROCESS_NAME = "PRODUCTS_SYNC";
	private static ProductDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new ProductDbService();
		
		syncDashboard.setIsBusy(true);
		
		dataService.addToProcessStack(PROCESS_NAME);
		
		ArrayList<Product> product = new ArrayList<>();
		
		try {
			
			product = dbService.getRecordsWithPullFromSapCheckedTrue(Product.class);
			
		} catch (SQLException e) {
			
			AppLogger.logError("An error occurred when querying products. " + e.getMessage());
			
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Product> products = response.getProducts();
				
				int productsCount = products.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + productsCount + " products from Salesforce for updating");
				
				try {
					if (productsCount > 0) {
						
						products = (ArrayList<Product>) updateSyncFields(products, false, false);
						
						dbService.upsertProductRecords(products);
					}
				} catch (SQLException e) {
					
					AppLogger.logError(e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Products sync complete");
					
				}
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Failed to push products to salesforce. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		dataService.pushProductsToSalesforce(Response.setProducts(product), callback);
		
	}
}
