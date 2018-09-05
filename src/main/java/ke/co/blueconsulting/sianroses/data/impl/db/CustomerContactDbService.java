package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbDataService;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerContactDbService extends BaseDbDataService<CustomerContact> {
	
	@Override
	protected Class<CustomerContact> getDaoServiceClass() {
		return CustomerContact.class;
	}
	
	public void upsertRecords(ArrayList<CustomerContact> customerContacts, GetCallback<ArrayList<CustomerContact>> callback) {
		
		try {
			
			TransactionManager.callInTransaction(connectionSource, () -> {
				
				ArrayList<CustomerContact> upsertedContacts = new ArrayList<>();
				
				for (CustomerContact customerContact : customerContacts) {
					
					boolean recordExists;
					
					boolean isUsingAccountNumberColumn = !StringUtils.isNullOrEmpty(customerContact.getAccountNumber());
					
					if (isUsingAccountNumberColumn) {
						recordExists = dao.queryBuilder().where()
								.eq("Account_Number", customerContact.getAccountNumber()).countOf() > 0;
					} else {
						recordExists = dao.queryBuilder().where()
								.eq("SalesForceId", customerContact.getSalesforceId()).countOf() > 0;
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
						
						updateBuilder.updateColumnValue("Pull_from_SAP__c", customerContact.isPullFromSAP());
						
						updateBuilder.updateColumnValue("Push_to_SAP__c", customerContact.isPushToSAP());
						
						if (!StringUtils.isNullOrEmpty(customerContact.getSalesforceId())) {
							updateBuilder.updateColumnValue("SalesforceId", new SelectArg(customerContact.getSalesforceId()));
						}
						
						if (!StringUtils.isNullOrEmpty(customerContact.getTitle())) {
							updateBuilder.updateColumnValue("Title", new SelectArg(customerContact.getTitle()));
						}
						
						if (isUsingAccountNumberColumn) {
							updateBuilder.where().eq("Account_Number", new SelectArg(customerContact.getAccountNumber()));
						} else {
							updateBuilder.where().eq("SalesForceId", new SelectArg(customerContact.getSalesforceId()));
						}
						
						updateBuilder.prepare();
						
						updateBuilder.update();
						
						QueryBuilder<CustomerContact, Integer> queryBuilder = dao.queryBuilder();
						
						Where<CustomerContact, Integer> where = queryBuilder.where();
						
						if (isUsingAccountNumberColumn) {
							where = where.eq("Account_Number", new SelectArg(customerContact.getAccountNumber()));
						} else {
							where = where.eq("SalesForceId", new SelectArg(customerContact.getSalesforceId()));
						}
						
						List<CustomerContact> insertedCustomerContactList = dao.query(where.prepare());
						
						customerContact = insertedCustomerContactList.get(0);
						
					} else {
						
						if (StringUtils.isNullOrEmpty(customerContact.getContactId())) {
							
							String fName = customerContact.getFirstName();
							
							String lName = customerContact.getLastName();
							
							customerContact.setContactId((StringUtils.isNullOrEmpty(fName) ? "" : fName)
									+ " " + (StringUtils.isNullOrEmpty(fName) ? "" : lName));
						}
						
						dao.createOrUpdate(customerContact);
					}
					upsertedContacts.add(customerContact);
				}
				callback.onCompleted(upsertedContacts);
				return null;
			});
			
		} catch (SQLException e) {
			callback.onError(e);
		} finally {
			callback.always();
		}
	}
}
