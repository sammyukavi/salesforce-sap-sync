package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.GreenhouseDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Greenhouse;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.SQLException;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;


public class Greenhouses {
	
	private static final String PROCESS_NAME = "GREENHOUSES_SYNC";
	private static GreenhouseDbService dbService;
	private static SyncContract.View syncDashboard;
	
	public static void sync(SyncContract.View view, SyncDataService dataService) {
		
		syncDashboard = view;
		
		dbService = new GreenhouseDbService();
		
		syncDashboard.setIsBusy(true);
		
		dataService.addToProcessStack(PROCESS_NAME);
		
		ArrayList<Greenhouse> greenhouses = new ArrayList<>();
		
		try {
			
			greenhouses = dbService.getRecordsWithPullFromSapCheckedTrue(Greenhouse.class);
			
		} catch (SQLException e) {
			
			AppLogger.logError("An error occurred when querying greenhouses. " + e.getMessage());
			
		}
		
		DataService.GetCallback<Response> pushWarehousesToSalesforceCallback = new DataService.GetCallback<Response>() {
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Greenhouse> greenhouses = response.getGreenhouses();
				
				int warehousesCount = greenhouses.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " + "Received " + warehousesCount + " greenhouses from Salesforce for updating");
				
				try {
					if (warehousesCount > 0) {
						
						greenhouses = (ArrayList<Greenhouse>) updateSyncFields(greenhouses, false, false);
						
						dbService.upsertRecords(greenhouses);
						
					}
				} catch (SQLException e) {
					
					AppLogger.logError("An error occurred when upserting greenhouses. " + e.getMessage());
					
				} finally {
					
					AppLogger.logInfo("Greenhouses sync is complete");
					
				}
			}
			
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("Failed to push greenhouses to Salesforce. " + t.getMessage());
				
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
			}
		};
		
		dataService.pushWarehousesToSalesforce(Response.setWarehouses(greenhouses), pushWarehousesToSalesforceCallback);
		
	}
}
