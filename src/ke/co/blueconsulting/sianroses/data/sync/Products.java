package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.ProductsDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateProductSyncFields;

public class Products {
	
	private static SyncDataService syncDataService;
	private static ProductsDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		syncDataService = dataService;
		dbService = new ProductsDbService();
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<Product> product = new ArrayList<>();
		
		try {
			product = dbService.getRecordsWithPullFromSAPCheckedTrue(Product.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				//Console.logToJson(response.getProducts());
				
				ArrayList<Product> products = response.getProducts();
				
				int productsCount = products.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + productsCount + " products from Salesforce for updating");
				
				if (productsCount > 0) {
					
					products = updateProductSyncFields(products, false, false);
					
					try {
						dbService.upsertProductRecords(products);
						AppLogger.logInfo("Products sync complete");
					} catch (SQLException e) {
						e.printStackTrace();
						AppLogger.logError(e.getMessage());
					}
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
		
		syncDataService.pushProductsToSalesforce(Response.setProducts(product), callback);
		
	}
}
