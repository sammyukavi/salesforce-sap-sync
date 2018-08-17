package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that is used to create a service for storing and retrieving the data
 * in the SAP server
 */
public class CustomerDbService extends BaseDbService {
	
	public CustomerDbService() {
		super();
	}
	
	public ArrayList<Customer> getUnsyncedCustomers(ArrayList<Long> ids) throws SQLException {
		
		Dao<Customer, Integer> dao = createDao(Customer.class);
		
		Where<Customer, Integer> where = dao.queryBuilder().where();
		
		where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
				where.eq("Pull_from_SAP__c", true));
		
		if (!ids.isEmpty()) {
			where = where.and().notIn("AUTOID", ids);
		}
		
		return (ArrayList<Customer>) dao.query(where.prepare());
		
	}
	
	public ArrayList<Customer> upsertCustomerRecords(ArrayList<Customer> customers) throws SQLException {
		
		Dao<Customer, Integer> dao = createDao(Customer.class);
		
		return TransactionManager.callInTransaction(connectionSource, () -> {
			
			ArrayList<Customer> upsertedCustomers = new ArrayList<>();
			
			for (Customer customer : customers) {
				
				boolean recordExists;
				boolean isUsingErpIdColumn = !StringUtils.isNullOrEmpty(customer.geteRPIdC());
				
				if (isUsingErpIdColumn) {
					recordExists = dao.queryBuilder().where()
							.eq("ERP_ID__c", customer.geteRPIdC()).countOf() > 0;
				} else {
					recordExists = dao.queryBuilder().where()
							.eq("SalesForceId", customer.getSalesforceId()).countOf() > 0;
				}
				
				if (recordExists) {
					
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
					
					updateBuilder.updateColumnValue("Active__c", customer.getActiveC());
					
					updateBuilder.updateColumnValue("Available_Credit_Amount", customer.getAvailableCreditAmount());
					
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
					
					updateBuilder.updateColumnValue("Credit_Limit__c", customer.getCreditLimitC());
					
					if (!StringUtils.isNullOrEmpty(customer.getCurrencyIsoCode())) {
						updateBuilder.updateColumnValue("CurrencyIsoCode", customer.getCurrencyIsoCode());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getDescription())) {
						updateBuilder.updateColumnValue("Description", customer.getDescription());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getEmailC()) && StringUtils.isValidEmailAddress(customer.getEmailC())) {
						updateBuilder.updateColumnValue("Email__c", customer.getEmailC());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.geteRPIdC())) {
						updateBuilder.updateColumnValue("ERP_ID__c", customer.geteRPIdC());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getGroupTypeC())) {
						updateBuilder.updateColumnValue("Group_Type__c", customer.getGroupTypeC());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", customer.getSalesforceId());
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getName())) {
						updateBuilder.updateColumnValue("Name", customer.getName());
					}
					
					updateBuilder.updateColumnValue("Outstanding_Balance__c", customer.getOutstandingBalanceC());
					
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
					
					updateBuilder.updateColumnValue("Prepaid_Amount__c", customer.getPrepaidAmountC());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", customer.isPullFromSAPC());
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", customer.isPushToSAPC());
					
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
					
					if (isUsingErpIdColumn) {
						updateBuilder.where().eq("ERP_ID__c", customer.geteRPIdC());
					} else {
						updateBuilder.where().eq("SalesForceId", customer.getSalesforceId());
					}
					
					updateBuilder.update();
					
					QueryBuilder<Customer, Integer> queryBuilder = dao.queryBuilder();
					
					Where<Customer, Integer> where = queryBuilder.where();
					
					if (isUsingErpIdColumn) {
						where = where.eq("ERP_ID__c", customer.geteRPIdC());
					} else {
						where = where.eq("SalesForceId", customer.getSalesforceId());
					}
					
					List<Customer> insertedCustomerList = dao.query(where.prepare());
					
					customer = insertedCustomerList.get(0);
					
				} else {
					dao.createOrUpdate(customer);
				}
				upsertedCustomers.add(customer);
			}
			return upsertedCustomers;
		});
	}
}
