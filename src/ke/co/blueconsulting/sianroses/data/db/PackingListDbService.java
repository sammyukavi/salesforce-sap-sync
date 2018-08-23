package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.ArInvoice;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PackingListDbService extends BaseDbService {
	
	public PackingListDbService(){
		super();
	}
	
	public ArrayList<ArInvoice> upsertRecords(ArrayList<ArInvoice> records) throws SQLException {
		
		Dao<ArInvoice, Integer> dao = createDao(ArInvoice.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<ArInvoice> upsertedPackingLists = new ArrayList<>();
			
			for (ArInvoice invoice : records) {
				
				boolean recordExists = dao.queryBuilder().where().eq("SalesForceId", invoice.getSalesforceId()).countOf() > 0;
				
				if (recordExists) {
					
					UpdateBuilder<ArInvoice, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(invoice.getAccountID())) {
						updateBuilder.updateColumnValue("AccountID", new SelectArg(invoice.getAccountID()));
					}
					
					updateBuilder.updateColumnValue("Posting_Date__c", new SelectArg(invoice.getPostingDateC()));
					
					if (!StringUtils.isNullOrEmpty(invoice.getpONumberC())) {
						updateBuilder.updateColumnValue("PO_Number__c", new SelectArg(invoice.getpONumberC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getFarmOrderNumberC())) {
						updateBuilder.updateColumnValue("Farm_Order_Number__c", new SelectArg(invoice.getFarmOrderNumberC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getInvoiceNumberC())) {
						updateBuilder.updateColumnValue("Invoice_Number__c", new SelectArg(invoice.getInvoiceNumberC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getInvoiceEntryC())) {
						updateBuilder.updateColumnValue("Invoice_Entry__c", new SelectArg(invoice.getInvoiceEntryC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getFarmC())) {
						updateBuilder.updateColumnValue("Farm_c", new SelectArg(invoice.getFarmC()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getAuction())) {
						updateBuilder.updateColumnValue("Auction", new SelectArg(invoice.getAuction()));
					}
					
					if (!StringUtils.isNullOrEmpty(invoice.getReason())) {
						updateBuilder.updateColumnValue("Reason", new SelectArg(invoice.getReason()));
					}
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", invoice.isPushToSap());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", invoice.isPullFromSap());
					
					updateBuilder.where().eq("SalesForceId", new SelectArg(invoice.getSalesforceId()));
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<ArInvoice, Integer> queryBuilder = dao.queryBuilder();
					
					Where<ArInvoice, Integer> where = queryBuilder.where();
					
					where = where.eq("SalesForceId", new SelectArg(invoice.getSalesforceId()));
					
					List<ArInvoice> insertedProductList = dao.query(where.prepare());
					
					invoice = insertedProductList.get(0);
					
				} else {
					dao.createOrUpdate(invoice);
				}
				upsertedPackingLists.add(invoice);
			}
			return upsertedPackingLists;
		});
	}
	
	public ArrayList<ArInvoice> getUnsyncedPackingLists(ArrayList<Integer> ids) throws SQLException {
		
		Dao<ArInvoice, Integer> dao = createDao(ArInvoice.class);
		
		Where<ArInvoice, Integer> where = dao.queryBuilder().where();
		
		where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
				where.eq("Pull_from_SAP__c", true));
		
		if (!ids.isEmpty()) {
			where = where.and().notIn("AUTOID", ids);
		}
		
		return (ArrayList<ArInvoice>) dao.query(where.prepare());
	}
}
