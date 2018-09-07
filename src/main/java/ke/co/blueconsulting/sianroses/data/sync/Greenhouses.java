package ke.co.blueconsulting.sianroses.data.sync;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.db.GreenhouseDbService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.salesforce.Greenhouse;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.UpdateFields.updateSyncFields;

public class Greenhouses {
	
	private final String PROCESS_NAME = "GREENHOUSES_SYNC";
	private SyncDataService syncDataService;
	private GreenhouseDbService dbService;
	private SyncContract.View syncDashboard;
	
	public Greenhouses(SyncContract.View syncDashboard, SyncDataService syncDataService) {
		
		this.syncDashboard = syncDashboard;
		
		this.syncDataService = syncDataService;
		
		this.dbService = new GreenhouseDbService();
		
		
	}
	
	public void sync() {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<ArrayList<Greenhouse>> getRecordsWithPullFromSapCheckedTrueCallback = new DataService.GetCallback<ArrayList<Greenhouse>>() {
			
			@Override
			public void onCompleted(ArrayList<Greenhouse> greenhouses) {
				
				pushToSaleforce(greenhouses);
			}
			
			@Override
			public void onError(Throwable t) {
				
				AppLogger.logError("An error occurred when querying greenhouses. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.getRecordsWithPullFromSapCheckedTrue(getRecordsWithPullFromSapCheckedTrueCallback);
		
	}
	
	private void pushToSaleforce(ArrayList<Greenhouse> greenhouses) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		DataService.GetCallback<Response> pushWarehousesToSalesforceCallback = new DataService.GetCallback<Response>() {
			
			@Override
			public void onCompleted(Response response) {
				
				ArrayList<Greenhouse> greenhouses = response.getGreenhouses();
				
				int warehousesCount = greenhouses.size();
				
				AppLogger.logInfo("Push To Salesforce Successful. " + "Received " + warehousesCount + " greenhouses from Salesforce for updating");
				
				upsertSap(greenhouses);
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("Failed to push greenhouses to Salesforce. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
				
			}
		};
		
		syncDataService.pushWarehousesToSalesforce(Response.setWarehouses(greenhouses), pushWarehousesToSalesforceCallback);
		
	}
	
	private void upsertSap(ArrayList<Greenhouse> greenhouses) {
		
		syncDashboard.setIsBusy(true);
		
		syncDataService.addToProcessStack(PROCESS_NAME);
		
		greenhouses = (ArrayList<Greenhouse>) updateSyncFields(greenhouses, false, false);
		
		DataService.GetCallback<ArrayList<Greenhouse>> upsertRecordsCallback = new DataService.GetCallback<ArrayList<Greenhouse>>() {
			
			@Override
			public void onCompleted(ArrayList<Greenhouse> response) {
				
				AppLogger.logInfo("Greenhouses sync is complete");
				
			}
			
			@Override
			public void onError(Throwable t) {
				AppLogger.logError("An error occurred when upserting greenhouses. " + t.getMessage());
			}
			
			@Override
			public void always() {
				
				syncDashboard.setIsBusy(false);
				
				syncDataService.removeFromProcessStack(PROCESS_NAME);
			}
		};
		
		dbService.upsertRecords(greenhouses, upsertRecordsCallback);
		
	}
}
