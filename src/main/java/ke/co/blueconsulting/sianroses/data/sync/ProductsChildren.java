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
	
	private final String PROCESS_NAME = "PRODUCTS_CHILDREN_SYNC";
	private ProductChildDbService dbService;
	private SyncContract.View syncDashboard;
	private SyncDataService syncDataService;
	
	public ProductsChildren(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		
		this.syncDashboard = syncDashboard;
		
		this.syncDataService = syncDataService;
		
		this.dbService = new ProductChildDbService();
	}
	
	public void sync() {
		
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
		
		dbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback);
		
	}
	
	private void pushToSalesforce(ArrayList<ProductChild> productsChildren) {
		
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
	
	private void upsertSap(ArrayList<ProductChild> productsChildren) {
		
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
		
		dbService.upsertRecords(productsChildren, upsertProductsCallback);
	}
}
