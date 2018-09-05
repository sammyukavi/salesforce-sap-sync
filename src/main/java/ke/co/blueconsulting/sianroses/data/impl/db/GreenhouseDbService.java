package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.Greenhouse;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreenhouseDbService extends BaseDbDataService<Greenhouse> {
	
	@Override
	protected Class<Greenhouse> getDaoServiceClass() {
		return Greenhouse.class;
	}
	
	
	public void upsertRecords(ArrayList<Greenhouse> greenhouses, GetCallback<ArrayList<Greenhouse>> callback) {
		
		try {
			
			TransactionManager.callInTransaction(connectionSource, () -> {
				
				ArrayList<Greenhouse> upsertedGreenhouses = new ArrayList<>();
				
				for (Greenhouse greenhouse : greenhouses) {
					
					boolean recordExists = dao.queryBuilder().where().eq("AUTOID", greenhouse.getAutoId()).countOf() > 0;
					
					if (recordExists) {
						
						UpdateBuilder<Greenhouse, Integer> updateBuilder = dao.updateBuilder();
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getSalesforceId())) {
							updateBuilder.updateColumnValue("SalesForceId", new SelectArg(greenhouse.getSalesforceId()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getFarm())) {
							updateBuilder.updateColumnValue("Farm__c", new SelectArg(greenhouse.getFarm()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getFarmCode())) {
							updateBuilder.updateColumnValue("Farm_Code__c", new SelectArg(greenhouse.getFarmCode()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getName())) {
							updateBuilder.updateColumnValue("Name", new SelectArg(greenhouse.getName()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getWarehouseCode())) {
							updateBuilder.updateColumnValue("Warehouse_Code__c", new SelectArg(greenhouse.getWarehouseCode()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getVarietyName())) {
							updateBuilder.updateColumnValue("Variety_Name__c", new SelectArg(greenhouse.getVarietyName()));
						}
						
						updateBuilder.updateColumnValue("Push_to_SAP__c", greenhouse.isPushToSap());
						
						updateBuilder.updateColumnValue("Pull_from_SAP__c", greenhouse.isPullFromSap());
						
						updateBuilder.where().eq("AUTOID", new SelectArg(greenhouse.getAutoId()));
						
						updateBuilder.prepare();
						
						updateBuilder.update();
						
						QueryBuilder<Greenhouse, Integer> queryBuilder = dao.queryBuilder();
						
						Where<Greenhouse, Integer> where = queryBuilder.where();
						
						where = where.eq("AUTOID", new SelectArg(greenhouse.getAutoId()));
						
						List<Greenhouse> insertedProductList = dao.query(where.prepare());
						
						greenhouse = insertedProductList.get(0);
						
					} else {
						dao.createOrUpdate(greenhouse);
					}
					upsertedGreenhouses.add(greenhouse);
				}
				callback.onCompleted(upsertedGreenhouses);
				return null;
			});
		} catch (SQLException e) {
			callback.onError(e);
		} finally {
			callback.always();
		}
		
	}
}
