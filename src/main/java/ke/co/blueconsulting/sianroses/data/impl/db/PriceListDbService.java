package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PriceListDbService extends BaseDbDataService<PriceList> {
	@Override
	protected Class<PriceList> getDaoServiceClass() {
		return PriceList.class;
	}
	
	public void upsertRecords(ArrayList<PriceList> priceLists, GetCallback<ArrayList<PriceList>> callback) {
		
		try {
			
			TransactionManager.callInTransaction(connectionSource, () -> {
				
				ArrayList<PriceList> upsertedPriceLists = new ArrayList<>();
				
				for (PriceList priceList : priceLists) {
					
					boolean recordExists = dao.queryBuilder().where().eq("AUTOID", priceList.getAutoId()).countOf() > 0;
					
					if (recordExists) {
						
						UpdateBuilder<PriceList, Integer> updateBuilder = dao.updateBuilder();
						
						if (!StringUtils.isNullOrEmpty(priceList.getSalesforceId())) {
							updateBuilder.updateColumnValue("SalesForceId", new SelectArg(priceList.getSalesforceId()));
						}
						
						if (!StringUtils.isNullOrEmpty(priceList.getCustomerCode())) {
							updateBuilder.updateColumnValue("CustomerCode", new SelectArg(priceList.getCustomerCode()));
						}
						
						if (!StringUtils.isNullOrEmpty(priceList.getProductCode())) {
							updateBuilder.updateColumnValue("productCode", new SelectArg(priceList.getProductCode()));
						}
						
						updateBuilder.updateColumnValue("UnitPrice", new SelectArg(priceList.getUnitPrice()));
						
						updateBuilder.updateColumnValue("CurrencyISOCode", priceList.getCurrencyISOCode());
						
						updateBuilder.updateColumnValue("isActive", new SelectArg(priceList.isActive()));
						
						updateBuilder.updateColumnValue("Push_to_SAP__c", priceList.isPushToSap());
						
						updateBuilder.updateColumnValue("Pull_from_SAP__c", priceList.isPullFromSap());
						
						updateBuilder.where().eq("AUTOID", new SelectArg(priceList.getAutoId()));
						
						updateBuilder.prepare();
						
						updateBuilder.update();
						
						QueryBuilder<PriceList, Integer> queryBuilder = dao.queryBuilder();
						
						Where<PriceList, Integer> where = queryBuilder.where();
						
						where = where.eq("AUTOID", new SelectArg(priceList.getAutoId()));
						
						List<PriceList> insertedProductList = dao.query(where.prepare());
						
						priceList = insertedProductList.get(0);
						
					} else {
						dao.createOrUpdate(priceList);
					}
					upsertedPriceLists.add(priceList);
				}
				callback.onCompleted(upsertedPriceLists);
				return null;
			});
		} catch (SQLException e) {
			callback.onError(e);
		} finally {
			callback.always();
		}
		
	}
}
