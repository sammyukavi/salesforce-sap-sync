package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.ProductChildDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.ProductChild;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class ProductsChildren {
	
	private static ProductChildDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		syncDashboard = view;
		dbService = new ProductChildDbService();
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<ProductChild> productChildren = new ArrayList<>();
		
		try {
			
			productChildren = dbService.getRecordsWithPullFromSapCheckedTrue(ProductChild.class);
			
		} catch (SQLException e) {
			
			AppLogger.logError("An error occurred when querying products child. " + e.getMessage());
			
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<ProductChild> productsChildren = response.getProductsChildren();
				
				int productsChildrenCount = productsChildren.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + productsChildrenCount + " products' children' children from Salesforce for updating");
				
				try {
					if (productsChildrenCount > 0) {
						
						productsChildren = (ArrayList<ProductChild>) updateSyncFields(productsChildren, false, false);
						
						dbService.upsertRecords(productsChildren);
					}
					
				} catch (SQLException e) {
					
					AppLogger.logError("An error occurred when upserting products child. " + e.getMessage());
					
				} finally {
					AppLogger.logInfo("Products' children sync complete");
				}
				
				
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Failed to push products' children to salesforce. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		dataService.pushProductsChildrenToSalesforce(Response.setProductsChildren(productChildren), callback);
		
	}
	
}
