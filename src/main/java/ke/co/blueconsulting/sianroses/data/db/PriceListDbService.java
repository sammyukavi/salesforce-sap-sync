package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
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

public class PriceListDbService extends BaseDbDataService {
	
	public PriceListDbService() {
		super();
	}
	
	public ArrayList<PriceList> upsertRecords(ArrayList<PriceList> products) throws SQLException {
		
		Dao<PriceList, Integer> dao = createDao(PriceList.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<PriceList> upsertedProducts = new ArrayList<>();
			
			for (PriceList productChild : products) {
				
				boolean recordExists = dao.queryBuilder().where().eq("AUTOID", productChild.getAutoId()).countOf() > 0;
				
				if (recordExists) {
					
					UpdateBuilder<PriceList, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(productChild.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", new SelectArg(productChild.getSalesforceId()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getCustomerCode())) {
						updateBuilder.updateColumnValue("CustomerCode", new SelectArg(productChild.getCustomerCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getProductCode())) {
						updateBuilder.updateColumnValue("productCode", new SelectArg(productChild.getProductCode()));
					}
					
					updateBuilder.updateColumnValue("UnitPrice", new SelectArg(productChild.getUnitPrice()));
					
					updateBuilder.updateColumnValue("CurrencyISOCode", productChild.getCurrencyISOCode());
					
					updateBuilder.updateColumnValue("isActive", new SelectArg(productChild.isActive()));
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", productChild.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", productChild.isPullFromSap());
					
					updateBuilder.where().eq("AUTOID", new SelectArg(productChild.getAutoId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<PriceList, Integer> queryBuilder = dao.queryBuilder();
					
					Where<PriceList, Integer> where = queryBuilder.where();
					
					where = where.eq("AUTOID", new SelectArg(productChild.getAutoId()));
					
					List<PriceList> insertedProductList = dao.query(where.prepare());
					
					productChild = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(productChild);
				}
				upsertedProducts.add(productChild);
			}
			return upsertedProducts;
		});
	}
	
}
