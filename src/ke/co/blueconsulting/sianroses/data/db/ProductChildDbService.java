package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.ProductChild;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductChildDbService extends BaseDbService {
	
	public ProductChildDbService() {
		super();
	}
	
	public ArrayList<ProductChild> upsertRecords(ArrayList<ProductChild> products) throws SQLException {
		
		Dao<ProductChild, Integer> dao = createDao(ProductChild.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<ProductChild> upsertedProducts = new ArrayList<>();
			
			for (ProductChild productChild : products) {
				
				boolean recordExists = dao.queryBuilder().where().eq("AUTOID", productChild.getAutoId()).countOf() > 0;
				
				if (recordExists) {
					
					UpdateBuilder<ProductChild, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(productChild.getFlowerCodeC())) {
						updateBuilder.updateColumnValue("Flower_Code__c", new SelectArg(productChild.getFlowerCodeC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(productChild.getName()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getColorC())) {
						updateBuilder.updateColumnValue("Color__c", new SelectArg(productChild.getColorC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getClassificationC())) {
						updateBuilder.updateColumnValue("Classification__c", new SelectArg(productChild.getClassificationC()));
					}
					
					updateBuilder.updateColumnValue("Is_Active__c", productChild.getIsActiveC());
					
					if (!StringUtils.isNullOrEmpty(productChild.getBreederC())) {
						updateBuilder.updateColumnValue("Breeder__c", new SelectArg(productChild.getBreederC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getProductFamilyC())) {
						updateBuilder.updateColumnValue("Product_Family__c", productChild.getProductFamilyC());
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getFarmC())) {
						updateBuilder.updateColumnValue("Farm__c", new SelectArg(productChild.getFarmC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getHeadSizeC())) {
						updateBuilder.updateColumnValue("Head_size__c", new SelectArg(productChild.getHeadSizeC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getConsumableStockC())) {
						updateBuilder.updateColumnValue("Consumable_Stock__c", new SelectArg(productChild.getConsumableStockC()));
					}
					
					if (!StringUtils.isNullOrEmpty(productChild.getLengthC())) {
						updateBuilder.updateColumnValue("Length__c", new SelectArg(productChild.getLengthC()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", productChild.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", productChild.isPullFromSap());
					
					if (!StringUtils.isNullOrEmpty(productChild.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", new SelectArg(productChild.getSalesforceId()));
					}
					
					updateBuilder.where().eq("AUTOID", new SelectArg(productChild.getAutoId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<ProductChild, Integer> queryBuilder = dao.queryBuilder();
					
					Where<ProductChild, Integer> where = queryBuilder.where();
					
					where = where.eq("AUTOID", new SelectArg(productChild.getAutoId()));
					
					List<ProductChild> insertedProductList = dao.query(where.prepare());
					
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
