package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * A class that is used to create a service for storing and retrieving the data
 * in the SAP server
 */
public class SAPDbService extends BaseDbService {
	
	public SAPDbService() {
		super();
	}
	
	public boolean testServerConnection(String serverAddress, String serverPort, String databaseName,
	                                    String databaseUsername, String databasePassword) throws ClassNotFoundException, SQLException {
		String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName="
				+ databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
		Connection connection = null;
		boolean status;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionUrl);
			status = true;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignored) {
				}
			}
		}
		return status;
	}
	
	public <S> ArrayList<S> insertRecords(Class<S> sClass, ArrayList<S> records) throws SQLException {
		ArrayList<S> insertedRecords = new ArrayList<>();
		Dao<S, Integer> dao = createDao(sClass);
		for (S record : records) {
			dao.createOrUpdate(record);
			insertedRecords.add(record);
		}
		return insertedRecords;
	}
	
	public <S> ArrayList<S> getRecordsWithoutNullOrEmptyColumn(Class<S> sClass) throws SQLException {
		String columnName = "SalesForceId";
		return getRecordsWithoutNullOrEmptyColumn(sClass, columnName);
	}
	
	@SuppressWarnings("unchecked")
	public <S> ArrayList<S> getRecordsWithoutNullOrEmptyColumn(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, "")).prepare());
	}
	
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass) throws SQLException {
		String columnName = "Pull_from_SAP__c";
		return getRecordsWithAFieldCheckedTrue(sClass, columnName);
	}
	
	@SuppressWarnings("unchecked")
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, true)).prepare());
	}
	
	public void updateCustomerRecords(ArrayList<Customer> customers) throws SQLException {
		Dao<Customer, Integer> dao = createDao(Customer.class);
		TransactionManager.callInTransaction(connectionSource,
				(Callable<Void>) () -> {
					for (Customer customer : customers) {
						
						if (!StringUtils.isNullOrEmpty(customer.geteRPIdC())) {
							
							UpdateBuilder<Customer, Integer> updateBuilder = dao.updateBuilder();
							
							if (!StringUtils.isNullOrEmpty(customer.getaRAccountC())) {
								updateBuilder.updateColumnValue("A_R_Account__c", customer.getaRAccountC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getAccountNumber())) {
								updateBuilder.updateColumnValue("Account_Number__c", customer.getAccountNumber());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getAccountPaymentTermsC())) {
								updateBuilder.updateColumnValue("Account_Payment_Terms__c", customer.getAccountPaymentTermsC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getActiveC())) {
								updateBuilder.updateColumnValue("Active__c", customer.getActiveC());
							}
							
							if (StringUtils.isNullOrEmpty(customer.getAvailableCreditAmount())) {
								updateBuilder.updateColumnValue("Available_Credit_Amount", customer.getAvailableCreditAmount());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getAddressIdC())) {
								updateBuilder.updateColumnValue("AddressID", customer.getAddressIdC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getBillingCity())) {
								updateBuilder.updateColumnValue("BillingCity", customer.getBillingCity());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getBillingCountry())) {
								updateBuilder.updateColumnValue("BillingCountry", customer.getBillingCountry());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getBillingPostalCode())) {
								updateBuilder.updateColumnValue("BillingPostalCode", customer.getBillingPostalCode());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getBillingState())) {
								updateBuilder.updateColumnValue("BillingState", customer.getBillingState());
							}
							
							if (StringUtils.isNullOrEmpty(customer.getCreditLimitC())) {
								updateBuilder.updateColumnValue("Credit_Limit__c", customer.getCreditLimitC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getCurrencyIsoCode())) {
								updateBuilder.updateColumnValue("CurrencyIsoCode", customer.getCurrencyIsoCode());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getDescription())) {
								updateBuilder.updateColumnValue("Description", customer.getDescription());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getEmailC()) && StringUtils.isValidEmailAddress(customer.getEmailC())) {
								updateBuilder.updateColumnValue("Email__c", customer.getEmailC());
							}
							
							if (StringUtils.isNullOrEmpty(customer.geteRPIdC())) {
								updateBuilder.updateColumnValue("ERP_ID__c", customer.geteRPIdC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getGroupTypeC())) {
								updateBuilder.updateColumnValue("Group_Type__c", customer.getGroupTypeC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getSalesForceId())) {
								updateBuilder.updateColumnValue("SalesForceId", customer.getSalesForceId());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getName())) {
								updateBuilder.updateColumnValue("Name", customer.getName());
							}
							
							if (StringUtils.isNullOrEmpty(customer.getOutstandingBalanceC())) {
								updateBuilder.updateColumnValue("Outstanding_Balance__c", customer.getOutstandingBalanceC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getOwnerId())) {
								updateBuilder.updateColumnValue("OwnerId", customer.getOwnerId());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getParentId())) {
								updateBuilder.updateColumnValue("ParentId", customer.getParentId());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getPaymentDeliveryConsolidationC())) {
								updateBuilder.updateColumnValue("Payment_Delivery_Consolidation__c", customer.getPaymentDeliveryConsolidationC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getPaymentTermsC())) {
								updateBuilder.updateColumnValue("Payment_Terms__c", customer.getPaymentTermsC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getPhone())) {
								updateBuilder.updateColumnValue("Phone", customer.getPhone());
							}
							
							if (StringUtils.isNullOrEmpty(customer.getPrepaidAmountC())) {
								updateBuilder.updateColumnValue("Prepaid_Amount__c", customer.getPrepaidAmountC());
							}
							
							if (StringUtils.isNullOrEmpty(customer.isPullFromSAPC())) {
								updateBuilder.updateColumnValue("Pull_from_SAP__c", customer.isPullFromSAPC());
							}
							
							if (StringUtils.isNullOrEmpty(customer.isPushToSAPC())) {
								updateBuilder.updateColumnValue("Push_to_SAP__c", customer.isPushToSAPC());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getShippingCity())) {
								updateBuilder.updateColumnValue("ShippingCity", customer.getShippingCity());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getShippingCountry())) {
								updateBuilder.updateColumnValue("ShippingCountry", customer.getShippingCountry());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getShippingPostalCode())) {
								updateBuilder.updateColumnValue("ShippingPostalCode", customer.getShippingPostalCode());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getShippingState())) {
								updateBuilder.updateColumnValue("ShippingState", customer.getShippingState());
							}
							
							if (!StringUtils.isNullOrEmpty(customer.getWebsite())) {
								updateBuilder.updateColumnValue("Website", customer.getWebsite());
							}
							
							updateBuilder.where().eq("ERP_ID__c", customer.geteRPIdC());
							updateBuilder.update();
							
						} else {
							dao.createOrUpdate(customer);
						}
					}
					return null;
				});
	}
	
	public void updateCustomerContactsRecords(ArrayList<CustomerContacts> customerContacts) throws SQLException {
		Dao<CustomerContacts, Integer> dao = createDao(CustomerContacts.class);
		TransactionManager.callInTransaction(connectionSource,
				(Callable<Void>) () -> {
					for (CustomerContacts customerContact : customerContacts) {
						
						if (!StringUtils.isNullOrEmpty(customerContact.getAccountNumber())) {
							
							UpdateBuilder<CustomerContacts, Integer> updateBuilder = dao.updateBuilder();
							
							if (!StringUtils.isNullOrEmpty(customerContact.getAccountId())) {
								updateBuilder.updateColumnValue("AccountID", customerContact.getAccountId());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getBirthDate())) {
								updateBuilder.updateColumnValue("BIRTHDATE", customerContact.getBirthDate());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getContactId())) {
								updateBuilder.updateColumnValue("CONTACTID", customerContact.getContactId());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getEmail())) {
								updateBuilder.updateColumnValue("Email", customerContact.getEmail());
							}
							
							if (StringUtils.isNullOrEmpty(customerContact.getFax())) {
								updateBuilder.updateColumnValue("Fax", customerContact.getFax());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getFirstName())) {
								updateBuilder.updateColumnValue("Firstname", customerContact.getFirstName());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getLastName())) {
								updateBuilder.updateColumnValue("Lastname", customerContact.getLastName());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getMailingCity())) {
								updateBuilder.updateColumnValue("MailingCity", customerContact.getMailingCity());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getMailingCountry())) {
								updateBuilder.updateColumnValue("MailingCountry", customerContact.getMailingCountry());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getMailingPostalCode())) {
								updateBuilder.updateColumnValue("BillingState", customerContact.getMailingPostalCode());
							}
							
							if (StringUtils.isNullOrEmpty(customerContact.getMailingState())) {
								updateBuilder.updateColumnValue("MailingState", customerContact.getMailingState());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getMailingStreet())) {
								updateBuilder.updateColumnValue("MailingStreet", customerContact.getMailingStreet());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getMobilePhone())) {
								updateBuilder.updateColumnValue("Mobile", customerContact.getMobilePhone());
							}
							
							if (!StringUtils.isNullOrEmpty(customerContact.getPhone())) {
								updateBuilder.updateColumnValue("Phone", customerContact.getPhone());
							}
							
							if (StringUtils.isNullOrEmpty(customerContact.isPullFromSap())) {
								updateBuilder.updateColumnValue("Pull_from_SAP__c", customerContact.isPullFromSap());
							}
							
							if (StringUtils.isNullOrEmpty(customerContact.isPushToSap())) {
								updateBuilder.updateColumnValue("Push_to_SAP__c", customerContact.isPushToSap());
							}
							
							if (StringUtils.isNullOrEmpty(customerContact.getTitle())) {
								updateBuilder.updateColumnValue("Title", customerContact.getTitle());
							}
							
							updateBuilder.where().eq("Account_Number", customerContact.getAccountNumber());
							updateBuilder.update();
							
						} else {
							dao.createOrUpdate(customerContact);
						}
					}
					return null;
				});
	}
}
