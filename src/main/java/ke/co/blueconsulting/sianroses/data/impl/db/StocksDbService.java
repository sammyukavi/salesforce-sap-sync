package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.Stock;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StocksDbService extends BaseDbDataService<Stock> {
	@Override
	protected Class<Stock> getDaoServiceClass() {
		return Stock.class;
	}
	
	public void upsertRecords(ArrayList<Stock> greenhouses, GetCallback<ArrayList<Stock>> callback) {
		
		try {
			
			TransactionManager.callInTransaction(connectionSource, () -> {
				
				ArrayList<Stock> upsertedStocks = new ArrayList<>();
				
				for (Stock greenhouse : greenhouses) {
					
					boolean recordExists = dao.queryBuilder().where().eq("AUTOID", greenhouse.getAutoId()).countOf() > 0;
					
					if (recordExists) {
						
						UpdateBuilder<Stock, Integer> updateBuilder = dao.updateBuilder();
						
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
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getWarehouse())) {
							updateBuilder.updateColumnValue("Warehouse__c", new SelectArg(greenhouse.getWarehouse()));
						}
						
						if (!StringUtils.isNullOrEmpty(greenhouse.getQuantity())) {
							updateBuilder.updateColumnValue("Quantity__c", new SelectArg(greenhouse.getQuantity()));
						}
						
						updateBuilder.updateColumnValue("Push_to_SAP__c", greenhouse.isPushToSap());
						
						updateBuilder.updateColumnValue("Pull_from_SAP__c", greenhouse.isPullFromSap());
						
						updateBuilder.where().eq("AUTOID", new SelectArg(greenhouse.getAutoId()));
						
						updateBuilder.prepare();
						
						updateBuilder.update();
						
						QueryBuilder<Stock, Integer> queryBuilder = dao.queryBuilder();
						
						Where<Stock, Integer> where = queryBuilder.where();
						
						where = where.eq("AUTOID", new SelectArg(greenhouse.getAutoId()));
						
						queryBuilder.setWhere(where);
						
						List<Stock> insertedProductList = dao.query(queryBuilder.prepare());
						
						greenhouse = insertedProductList.get(0);
						
					} else {
						dao.createOrUpdate(greenhouse);
					}
					upsertedStocks.add(greenhouse);
				}
				callback.onCompleted(upsertedStocks);
				return null;
			});
		} catch (SQLException e) {
			callback.onError(e);
		} finally {
			callback.always();
		}
		
	}
	
	public void getRecordsWithPullFromSapCheckedTrue(GetCallback<ArrayList<Stock>> callback, long limit, long startId) {
		try {
			QueryBuilder<Stock, Integer> queryBuilder = dao.queryBuilder();
			Where<Stock, Integer> where = queryBuilder.where();
			queryBuilder.setWhere(where.or(where.isNull("Pull_from_SAP__c"),
					where.eq("Pull_from_SAP__c", true)).and().gt("AUTOID", startId));
			queryBuilder.orderBy("AUTOID", true);
			if (limit != 0) {
				queryBuilder.limit(limit);
			}
			if (callback != null) {
				callback.onCompleted((ArrayList<Stock>) dao.query(queryBuilder.prepare()));
			}
		} catch (SQLException e) {
			if (callback != null) {
				callback.onError(e);
			}
		} finally {
			if (callback != null) {
				callback.always();
			}
		}
		
	}
	
}
