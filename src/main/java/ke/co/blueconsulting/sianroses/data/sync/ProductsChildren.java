package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.ProductChildDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.ProductChild;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class ProductsChildren {
	
	private static final String PROCESS_NAME = "PRODUCTS_CHILDREN_SYNC";
	private static ProductChildDbService productChildDbService;
	private static SyncContract.View syncDashboard;
	private static SyncDataService syncDataService;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		productChildDbService = new ProductChildDbService();
		
		syncDataService = dataService;
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		
		DataService.GetCallback<ArrayList<ProductChild>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<ProductChild>>() {
			
			@Override
			public void onCompleted(ArrayList<ProductChild> productsChildren) {
				
				pushToSalesforce(productsChildren);
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("An error occurred when querying products child. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		productChildDbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback);
		
	}
	
	private static void pushToSalesforce(ArrayList<ProductChild> productsChildren) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> pushProductsChildrenToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<ProductChild> productsChildren = response.getProductsChildren();
				
				int productsChildrenCount = productsChildren.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " +
						"Received " + productsChildrenCount + " products' children' children from Salesforce for updating");
				
				upsertSap(productsChildren);
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occurred when upserting customer records. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		syncDataService.pushProductsChildrenToSalesforce(Response.setProductsChildren(productsChildren),
				pushProductsChildrenToSalesforceCallback);
	}
	
	private static void upsertSap(ArrayList<ProductChild> productsChildren) {
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		syncDashboard.setIsBusy(true);
		
		productsChildren = (ArrayList<ProductChild>) updateSyncFields(productsChildren, false, false);
		
		DataService.GetCallback<ArrayList<ProductChild>> upsertProductsCallback = new DataService.GetCallback<ArrayList<ProductChild>>() {
			
			@Override
			public void onCompleted(ArrayList<ProductChild> returnedProducts) {
				
				AppLogger.logInfo("Products' children sync complete");
				
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
		
		productChildDbService.upsertRecords(productsChildren, upsertProductsCallback);
	}
}
