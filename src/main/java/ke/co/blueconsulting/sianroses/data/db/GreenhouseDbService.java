package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Greenhouse;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreenhouseDbService extends BaseDbService {
	
	public GreenhouseDbService() {
		super();
	}
	
	public ArrayList<Greenhouse> upsertRecords(ArrayList<Greenhouse> greenhouses) throws SQLException {
		
		Dao<Greenhouse, Integer> dao = createDao(Greenhouse.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<Greenhouse> upsertedProducts = new ArrayList<>();
			
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
				upsertedProducts.add(greenhouse);
			}
			return upsertedProducts;
		});
	}
	
}
