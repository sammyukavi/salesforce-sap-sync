package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingListItem;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackingListItemDbService extends BaseDbDataService {
	
	public PackingListItemDbService() {
		super();
	}
	
	public ArrayList<PackingListItem> upsertRecords(ArrayList<PackingListItem> records, int autoIdMst) throws SQLException {
		
		Dao<PackingListItem, Integer> dao = createDao(PackingListItem.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<PackingListItem> upsertedPackingListItems = new ArrayList<>();
			
			for (PackingListItem packingListItem : records) {
				
				boolean recordExists = dao.queryBuilder().where().eq("SalesForceId", packingListItem.getSalesforceId()).countOf() > 0;
				
				packingListItem.setAutoIdMst(autoIdMst);
				
				if (recordExists) {
					
					UpdateBuilder<PackingListItem, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(packingListItem.getFlowerCode())) {
						updateBuilder.updateColumnValue("Flower_Code__c", new SelectArg(packingListItem.getFlowerCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(packingListItem.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(packingListItem.getName()));
					}
					
					if (!StringUtils.isNullOrEmpty(packingListItem.getQuantity())) {
						updateBuilder.updateColumnValue("Quantity__c", new SelectArg(packingListItem.getQuantity()));
					}
					
					if (!StringUtils.isNullOrEmpty(packingListItem.getUnitPrice())) {
						updateBuilder.updateColumnValue("UnitPrice", new SelectArg(packingListItem.getUnitPrice()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", packingListItem.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", packingListItem.isPullFromSap());
					
					updateBuilder.where().eq("SalesForceId", new SelectArg(packingListItem.getSalesforceId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<PackingListItem, Integer> queryBuilder = dao.queryBuilder();
					
					Where<PackingListItem, Integer> where = queryBuilder.where();
					
					where = where.eq("SalesForceId", new SelectArg(packingListItem.getSalesforceId()));
					
					List<PackingListItem> insertedProductList = dao.query(where.prepare());
					
					packingListItem = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(packingListItem);
				}
				upsertedPackingListItems.add(packingListItem);
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
