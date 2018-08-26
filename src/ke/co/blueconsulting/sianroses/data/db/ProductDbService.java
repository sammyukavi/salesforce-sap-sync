package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDbService extends BaseDbService {
	
	public ProductDbService() {
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
						updateBuilder.updateColumnValue("productcode", new SelectArg(product.getProductCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(product.getName()));
					}
					
					updateBuilder.updateColumnValue("IsActive", product.isActive());
					
					if (!StringUtils.isNullOrEmpty(product.getColor())) {
						updateBuilder.updateColumnValue("Color__c", new SelectArg(product.getColor()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getClassification())) {
						updateBuilder.updateColumnValue("Classification__c", new SelectArg(product.getClassification()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getBreeder())) {
						updateBuilder.updateColumnValue("Breeder__c", new SelectArg(product.getBreeder()));
					}
					
					/*if (!StringUtils.isNullOrEmpty(product.getFamily())) {
						updateBuilder.updateColumnValue("Family", product.getFamily());
					}*/
					
					if (!StringUtils.isNullOrEmpty(product.getProductType())) {
						updateBuilder.updateColumnValue("Product_Type__c", new SelectArg(product.getProductType()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getParentProduct())) {
						updateBuilder.updateColumnValue("Parent_Product__c", new SelectArg(product.getParentProduct()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getParentProductCode())) {
						updateBuilder.updateColumnValue("Parent_Product_Code__c", new SelectArg(product.getParentProductCode()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", product.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", product.isPullFromSap());
					
					if (!StringUtils.isNullOrEmpty(product.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", new SelectArg(product.getSalesforceId()));
					}
					
					if (!StringUtils.isNullOrEmpty(product.getProductCategory())) {
						updateBuilder.updateColumnValue("product_Category", new SelectArg(product.getProductCategory()));
					}
					
					if (isUsingProductCodeColumn) {
						updateBuilder.where().eq("productcode", new SelectArg(product.getProductCode()));
					} else {
						updateBuilder.where().eq("SalesForceId", new SelectArg(product.getSalesforceId()));
					}
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<Product, Integer> queryBuilder = dao.queryBuilder();
					
					Where<Product, Integer> where = queryBuilder.where();
					
					if (isUsingProductCodeColumn) {
						where = where.eq("productcode", new SelectArg(product.getProductCode()));
					} else {
						where = where.eq("SalesForceId", new SelectArg(product.getSalesforceId()));
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
