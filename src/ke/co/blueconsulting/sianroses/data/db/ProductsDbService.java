package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDbService extends BaseDbService {
	
	public ProductsDbService() {
		super();
	}
	
	public ArrayList<Product> upsertProductRecords(ArrayList<Product> products) throws SQLException {
		
		Dao<Product, Integer> dao = createDao(Product.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<Product> upsertedProducts = new ArrayList<>();
			
			for (Product product : products) {
				
				boolean recordExists;
				boolean isUsingProductCodeColumn = (!StringUtils.isNullOrEmpty(product.getProductCode()));
				
				if (isUsingProductCodeColumn) {
					recordExists = dao.queryBuilder().where()
							.eq("productcode", product.getProductCode()).countOf() > 0;
				} else {
					recordExists = dao.queryBuilder().where()
							.eq("SalesForceId", product.getSalesforceId()).countOf() > 0;
				}
				
				if (recordExists) {
					//product exists, update
					
					UpdateBuilder<Product, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(product.getProductCode())) {
						updateBuilder.updateColumnValue("productcode", product.getProductCode());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getName())) {
						updateBuilder.updateColumnValue("Name", product.getName());
					}
					
					updateBuilder.updateColumnValue("IsActive", product.isActive());
					
					if (!StringUtils.isNullOrEmpty(product.getColorC())) {
						updateBuilder.updateColumnValue("Color__c", product.getColorC());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getClassificationC())) {
						updateBuilder.updateColumnValue("Classification__c", product.getClassificationC());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getBreederC())) {
						updateBuilder.updateColumnValue("Breeder__c", product.getBreederC());
					}
					
					/*if (!StringUtils.isNullOrEmpty(product.getFamily())) {
						updateBuilder.updateColumnValue("Family", product.getFamily());
					}*/
					
					if (!StringUtils.isNullOrEmpty(product.getProductTypeC())) {
						updateBuilder.updateColumnValue("Product_Type__c", product.getProductTypeC());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getParentProductC())) {
						updateBuilder.updateColumnValue("Parent_Product__c", product.getParentProductC());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getParentProductCodeC())) {
						updateBuilder.updateColumnValue("Parent_Product_Code__c", product.getParentProductCodeC());
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", product.isPushToSAPC());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", product.isPullFromSAPC());
					
					if (!StringUtils.isNullOrEmpty(product.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", product.getSalesforceId());
					}
					
					if (!StringUtils.isNullOrEmpty(product.getProductCategory())) {
						updateBuilder.updateColumnValue("product_Category", product.getProductCategory());
					}
					
					if (isUsingProductCodeColumn) {
						updateBuilder.where().eq("productcode", product.getProductCode());
					} else {
						updateBuilder.where().eq("SalesForceId", product.getSalesforceId());
					}
					
					updateBuilder.update();
					
					QueryBuilder<Product, Integer> queryBuilder = dao.queryBuilder();
					
					Where<Product, Integer> where = queryBuilder.where();
					
					if (isUsingProductCodeColumn) {
						where = where.eq("productcode", product.getProductCode());
					} else {
						where = where.eq("SalesForceId", product.getSalesforceId());
					}
					
					List<Product> insertedProductList = dao.query(where.prepare());
					
					product = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(product);
				}
				upsertedProducts.add(product);
			}
			return upsertedProducts;
		});
	}
	
}
