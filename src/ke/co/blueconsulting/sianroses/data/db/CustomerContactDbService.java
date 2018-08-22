package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to store and read a customer's contacts from the SAP server
 */
public class CustomerContactDbService extends BaseDbService {
	
	public CustomerContactDbService() {
		super();
	}
	
	public ArrayList<CustomerContact> getUnsyncedCustomerContacts(ArrayList<Long> ids) throws SQLException {
		
		Dao<CustomerContact, Integer> dao = createDao(CustomerContact.class);
		
		Where<CustomerContact, Integer> where = dao.queryBuilder().where();
		
		where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
				where.eq("Pull_from_SAP__c", true)).and().isNotNull("LastName");
		
		if (!ids.isEmpty()) {
			where = where.and().notIn("AUTOID", ids);
		}
		
		return (ArrayList<CustomerContact>) dao.query(where.prepare());
		
	}
	
	public ArrayList<CustomerContact> upsertCustomerRecords(ArrayList<CustomerContact> customerContacts) throws SQLException {
		
		Dao<CustomerContact, Integer> dao = createDao(CustomerContact.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<CustomerContact> upsertedContacts = new ArrayList<>();
			
			for (CustomerContact customerContact : customerContacts) {
				
				boolean recordExists;
				
				boolean isUsingSalesforceIdColumn = (!StringUtils.isNullOrEmpty(customerContact.getSalesforceId()));
				
				if (isUsingSalesforceIdColumn) {
					recordExists = dao.queryBuilder().where()
							.eq("SalesForceId", customerContact.getSalesforceId()).countOf() > 0;
				} else {
					recordExists = dao.queryBuilder().where()
							.eq("Account_Number", customerContact.getAccountNumber()).countOf() > 0;
				}
				
				if (recordExists) {
					
					UpdateBuilder<CustomerContact, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(customerContact.getAccountId())) {
						updateBuilder.updateColumnValue("AccountID", new SelectArg(customerContact.getAccountId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getAccountNumber())) {
						updateBuilder.updateColumnValue("Account_Number", new SelectArg(customerContact.getAccountNumber()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getBirthDate())) {
						updateBuilder.updateColumnValue("BIRTHDATE", new SelectArg(customerContact.getBirthDate()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getContactId())) {
						updateBuilder.updateColumnValue("CONTACTID", new SelectArg(customerContact.getContactId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getDepartment())) {
						updateBuilder.updateColumnValue("Department", new SelectArg(customerContact.getDepartment()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getEmail())) {
						updateBuilder.updateColumnValue("Email", new SelectArg(customerContact.getEmail()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getFax())) {
						updateBuilder.updateColumnValue("Fax", new SelectArg(customerContact.getFax()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getFirstName())) {
						updateBuilder.updateColumnValue("Firstname", new SelectArg(customerContact.getFirstName()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getLastName())) {
						updateBuilder.updateColumnValue("Lastname", new SelectArg(customerContact.getLastName()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMailingCity())) {
						updateBuilder.updateColumnValue("MailingCity", new SelectArg(customerContact.getMailingCity()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMailingCountry())) {
						updateBuilder.updateColumnValue("MailingCountry", new SelectArg(customerContact.getMailingCountry()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMailingPostalCode())) {
						updateBuilder.updateColumnValue("MailingPostalCode", new SelectArg(customerContact.getMailingPostalCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMailingState())) {
						updateBuilder.updateColumnValue("MailingState", new SelectArg(customerContact.getMailingState()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMailingStreet())) {
						updateBuilder.updateColumnValue("MailingStreet", new SelectArg(customerContact.getMailingStreet()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getMobilePhone())) {
						updateBuilder.updateColumnValue("Mobile", new SelectArg(customerContact.getMobilePhone()));
					}
					
					if (!StringUtils.isNullOrEmpty(customerContact.getPhone())) {
						updateBuilder.updateColumnValue("Phone", new SelectArg(customerContact.getPhone()));
					}
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", customerContact.isPullFromSap());
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", customerContact.isPushToSap());
					
					if (StringUtils.isNullOrEmpty(customerContact.getTitle())) {
						updateBuilder.updateColumnValue("Title", new SelectArg(customerContact.getTitle()));
					}
					
					if (isUsingSalesforceIdColumn) {
						updateBuilder.where().eq("SalesForceId", new SelectArg(customerContact.getSalesforceId()));
					} else {
						updateBuilder.where().eq("Account_Number", new SelectArg(customerContact.getAccountNumber()));
					}
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<CustomerContact, Integer> queryBuilder = dao.queryBuilder();
					
					Where<CustomerContact, Integer> where = queryBuilder.where();
					
					if (isUsingSalesforceIdColumn) {
						where = where.eq("SalesForceId", new SelectArg(customerContact.getSalesforceId()));
					} else {
						where = where.eq("Account_Number", new SelectArg(customerContact.getAccountNumber()));
					}
					
					List<CustomerContact> insertedCustomerContactList = dao.query(where.prepare());
					
					customerContact = insertedCustomerContactList.get(0);
					
				} else {
					dao.createOrUpdate(customerContact);
				}
				upsertedContacts.add(customerContact);
			}
			return upsertedContacts;
		});
	}
}
