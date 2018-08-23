package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Warehouse;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDbService extends BaseDbService {
	
	public WarehouseDbService() {
		super();
	}
	
	public ArrayList<Warehouse> upsertRecords(ArrayList<Warehouse> warehouses) throws SQLException {
		
		Dao<Warehouse, Integer> dao = createDao(Warehouse.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<Warehouse> upsertedProducts = new ArrayList<>();
			
			for (Warehouse warehouse : warehouses) {
				
				boolean recordExists = dao.queryBuilder().where().eq("AUTOID", warehouse.getAutoId()).countOf() > 0;
				
				if (recordExists) {
					
					UpdateBuilder<Warehouse, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(warehouse.getSalesForceId())) {
						updateBuilder.updateColumnValue("SalesForceId", new SelectArg(warehouse.getSalesForceId()));
					}
					
					if (!StringUtils.isNullOrEmpty(warehouse.getFarmC())) {
						updateBuilder.updateColumnValue("Farm__c", new SelectArg(warehouse.getFarmC()));
					}
					
					if (!StringUtils.isNullOrEmpty(warehouse.getFarmCodeC())) {
						updateBuilder.updateColumnValue("Farm_Code__c", new SelectArg(warehouse.getFarmCodeC()));
					}
					
					if (!StringUtils.isNullOrEmpty(warehouse.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(warehouse.getName()));
					}
					
					if (!StringUtils.isNullOrEmpty(warehouse.getWarehouseCodeC())) {
						updateBuilder.updateColumnValue("Warehouse_Code__c", new SelectArg(warehouse.getWarehouseCodeC()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", warehouse.isPushToSAPC());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", warehouse.isPullFromSAPC());
					
					updateBuilder.where().eq("AUTOID", new SelectArg(warehouse.getAutoId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<Warehouse, Integer> queryBuilder = dao.queryBuilder();
					
					Where<Warehouse, Integer> where = queryBuilder.where();
					
					where = where.eq("AUTOID", new SelectArg(warehouse.getAutoId()));
					
					List<Warehouse> insertedProductList = dao.query(where.prepare());
					
					warehouse = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(warehouse);
				}
				upsertedProducts.add(warehouse);
			}
			return upsertedProducts;
		});
	}
	
}
