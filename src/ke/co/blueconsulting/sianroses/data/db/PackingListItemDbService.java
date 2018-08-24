package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingListItem;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackingListItemDbService extends BaseDbService {
	
	public PackingListItemDbService() {
		super();
	}
	
	public ArrayList<PackingListItem> upsertRecords(ArrayList<PackingListItem> records) throws SQLException {
		
		Dao<PackingListItem, Integer> dao = createDao(PackingListItem.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<PackingListItem> upsertedPackingListItems = new ArrayList<>();
			
			for (PackingListItem invoice : records) {
				
				boolean recordExists = dao.queryBuilder().where().eq("SalesForceId", invoice.getSalesforceId()).countOf() > 0;
				
				if (recordExists) {
					
					UpdateBuilder<PackingListItem, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(invoice.getFlowerCodeC())) {
						updateBuilder.updateColumnValue("Flower_Code__c", new SelectArg(invoice.getFlowerCodeC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(invoice.getName()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getQuantityC())) {
						updateBuilder.updateColumnValue("Quantity__c", new SelectArg(invoice.getQuantityC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getUnitPrice())) {
						updateBuilder.updateColumnValue("UnitPrice", new SelectArg(invoice.getUnitPrice()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", invoice.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", invoice.isPullFromSap());
					
					updateBuilder.where().eq("SalesForceId", new SelectArg(invoice.getSalesforceId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<PackingListItem, Integer> queryBuilder = dao.queryBuilder();
					
					Where<PackingListItem, Integer> where = queryBuilder.where();
					
					where = where.eq("SalesForceId", new SelectArg(invoice.getSalesforceId()));
					
					List<PackingListItem> insertedProductList = dao.query(where.prepare());
					
					invoice = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(invoice);
				}
				upsertedPackingListItems.add(invoice);
			}
			return upsertedPackingListItems;
		});
	}
	
	public ArrayList<PackingListItem> getUnsyncedPackingListItems(ArrayList<Integer> ids) throws SQLException {
		
		Dao<PackingListItem, Integer> dao = createDao(PackingListItem.class);
		
		Where<PackingListItem, Integer> where = dao.queryBuilder().where();
		
		where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
				where.eq("Pull_from_SAP__c", true));
		
		if (!ids.isEmpty()) {
			where = where.and().notIn("AUTOID", ids);
		}
		
		return (ArrayList<PackingListItem>) dao.query(where.prepare());
	}
	
	public PackingListItem insertRecord(PackingListItem packingList) throws SQLException {
		return insertRecord(PackingListItem.class, packingList);
	}
}
