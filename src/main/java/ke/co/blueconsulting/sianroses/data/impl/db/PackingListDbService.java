package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.PackingList;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackingListDbService extends BaseDbDataService<PackingList> {
	
	@Override
	protected Class<PackingList> getDaoServiceClass() {
		return PackingList.class;
	}
	
	public void upsertRecords(ArrayList<PackingList> packingLists, GetCallback<ArrayList<PackingList>> callback) {
		
		try {
			TransactionManager.callInTransaction(connectionSource, () -> {
				
				ArrayList<PackingList> upsertedPackingLists = new ArrayList<>();
				
				for (PackingList packingList : packingLists) {
					
					boolean recordExists = dao.queryBuilder().where().eq("SalesForceId", packingList.getSalesforceId()).countOf() > 0;
					
					if (recordExists) {
						
						UpdateBuilder<PackingList, Integer> updateBuilder = dao.updateBuilder();
						
						updateBuilder.updateColumnValue("Posting_Date__c", new SelectArg(packingList.getPostingDate()));
						
						if (!StringUtils.isNullOrEmpty(packingList.getErpId())) {
							updateBuilder.updateColumnValue("ERP_ID__c", new SelectArg(packingList.getErpId()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getAccountID())) {
							updateBuilder.updateColumnValue("AccountID", new SelectArg(packingList.getAccountID()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getDueDate())) {
							updateBuilder.updateColumnValue("Due_Date__c", new SelectArg(packingList.getDueDate()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getFarmOrderNumber())) {
							updateBuilder.updateColumnValue("Farm_Order_Number__c", new SelectArg(packingList.getFarmOrderNumber()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getPONumber())) {
							updateBuilder.updateColumnValue("PO_Number__c", new SelectArg(packingList.getPONumber()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getInvoiceNumber())) {
							updateBuilder.updateColumnValue("Invoice_Number__c", new SelectArg(packingList.getInvoiceNumber()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getInvoiceEntry())) {
							updateBuilder.updateColumnValue("Invoice_Entry__c", new SelectArg(packingList.getInvoiceEntry()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getFarm())) {
							updateBuilder.updateColumnValue("Farm_c", new SelectArg(packingList.getFarm()));
						}
						
						if (!StringUtils.isNullOrEmpty(packingList.getReason())) {
							updateBuilder.updateColumnValue("Reason", new SelectArg(packingList.getReason()));
						}
						if (!StringUtils.isNullOrEmpty(packingList.getAuction())) {
							updateBuilder.updateColumnValue("Auction", new SelectArg(packingList.getAuction()));
						}
						
						updateBuilder.updateColumnValue("Push_to_SAP__c", packingList.isPushToSap());
						
						updateBuilder.updateColumnValue("Pull_from_SAP__c", packingList.isPullFromSap());
						
						updateBuilder.where().eq("SalesForceId", new SelectArg(packingList.getSalesforceId()));
						
						updateBuilder.prepare();
						
						updateBuilder.update();
						
						QueryBuilder<PackingList, Integer> queryBuilder = dao.queryBuilder();
						
						Where<PackingList, Integer> where = queryBuilder.where();
						
						where = where.eq("SalesForceId", new SelectArg(packingList.getSalesforceId()));
						
						queryBuilder.setWhere(where);
						
						List<PackingList> insertedProductList = dao.query(queryBuilder.prepare());
						
						packingList = mergeObject(packingList, insertedProductList.get(0));
						
					} else {
						dao.createOrUpdate(packingList);
					}
					
					upsertedPackingLists.add(packingList);
				}
				callback.onCompleted(upsertedPackingLists);
				return null;
			});
		} catch (SQLException e) {
			callback.onError(e);
		} finally {
			callback.always();
		}
	}
	
	private PackingList mergeObject(PackingList packingList1, PackingList packingList2) {
		
		PackingList packingList = new PackingList();
		
		packingList.setAutoId(packingList1.getAutoId() != 0 ? packingList1.getAutoId() : packingList2.getAutoId());
		
		packingList.setPullFromSap(packingList1.isPullFromSap() || packingList2.isPullFromSap());
		
		packingList.setPushToSap(packingList1.isPushToSap() || packingList2.isPushToSap());
		
		packingList.setSalesforceId(packingList1.getSalesforceId() != null ? packingList1.getSalesforceId() : packingList2.getSalesforceId());
		
		packingList.setPostingDate(packingList1.getPostingDate() != null ? packingList1.getPostingDate() : packingList2.getPostingDate());
		
		packingList.setErpId(packingList1.getErpId() != null ? packingList1.getErpId() : packingList2.getErpId());
		
		packingList.setAccountID(packingList1.getAccountID() != null ? packingList1.getAccountID() : packingList2.getAccountID());
		
		packingList.setDueDate(packingList1.getDueDate() != null ? packingList1.getDueDate() : packingList2.getDueDate());
		
		packingList.setFarmOrderNumber(packingList1.getFarmOrderNumber() != null ? packingList1.getFarmOrderNumber() : packingList2.getFarmOrderNumber());
		
		packingList.setPONumber(packingList1.getPONumber() != null ? packingList1.getPONumber() : packingList2.getPONumber());
		
		packingList.setInvoiceNumber(packingList1.getInvoiceNumber() != null ? packingList1.getInvoiceNumber() : packingList2.getInvoiceNumber());
		
		packingList.setInvoiceEntry(packingList1.getInvoiceEntry() != null ? packingList1.getInvoiceEntry() : packingList2.getInvoiceEntry());
		
		packingList.setFarm(packingList1.getFarm() != null ? packingList1.getFarm() : packingList2.getFarm());
		
		packingList.setReason(packingList1.getReason() != null ? packingList1.getReason() : packingList2.getReason());
		
		packingList.setAuction(packingList1.getAuction() != null ? packingList1.getAuction() : packingList2.getAuction());
		
		packingList.setPackingListItems(packingList1.getPackingListItems() != null ? packingList1.getPackingListItems() : packingList2.getPackingListItems());
		
		return packingList;
	}
	
}
