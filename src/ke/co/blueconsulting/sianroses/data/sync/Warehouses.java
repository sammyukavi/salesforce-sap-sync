package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.WarehouseDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Warehouse;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;


public class Warehouses {
	
	private static WarehouseDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new WarehouseDbService();
		
		syncDashboard.setIsBusy(true);
		
		ArrayList<Warehouse> warehouses = new ArrayList<>();
		
		try {
			warehouses = dbService.getRecordsWithPullFromSAPCheckedTrue(Warehouse.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DataService.GetCallback<Response> callback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Warehouse> warehouses = response.getWarehouses();
				
				int warehousesCount = warehouses.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " + "Received " + warehousesCount + " warehouses from Salesforce for updating");
				
				try {
					if (warehousesCount > 0) {
						warehouses = (ArrayList<Warehouse>) updateSyncFields(warehouses, false, false);
						dbService.upsertRecords(warehouses);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					AppLogger.logError(e.getMessage());
				} finally {
					AppLogger.logInfo("Warehouses sync is complete");
				}
			}
			
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to push warehouses to Salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				syncDashboard.setIsBusy(false);
			}
		};
		dataService.pushWarehousesToSalesforce(Response.setWarehouses(warehouses), callback);
		
	}
}
