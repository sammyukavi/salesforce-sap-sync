package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
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
	
	public ArrayList<Customer> getUnsyncedCustomers(ArrayList<Integer> ids) throws SQLException {
		
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
				boolean isUsingErpIdColumn = !StringUtils.isNullOrEmpty(customer.getErpId());
				
				if (isUsingErpIdColumn) {
					recordExists = dao.queryBuilder().where()
							.eq("ERP_ID__c", customer.getErpId()).countOf() > 0;
				} else {
					recordExists = dao.queryBuilder().where()
							.eq("SalesForceId", customer.getSalesforceId()).countOf() > 0;
				}
				
				if (recordExists) {
					
					UpdateBuilder<Customer, Integer> updateBuilder = dao.updateBuilder();
					
					if (!StringUtils.isNullOrEmpty(customer.getaRAccountC())) {
						updateBuilder.updateColumnValue("A_R_Account__c", new SelectArg(customer.getaRAccountC()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getAccountNumber())) {
						updateBuilder.updateColumnValue("Account_Number__c", new SelectArg(customer.getAccountNumber()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getAccountPaymentTerms())) {
						updateBuilder.updateColumnValue("Account_Payment_Terms__c", new SelectArg(customer.getAccountPaymentTerms()));
					}
					
					updateBuilder.updateColumnValue("Active__c", customer.getActiveC());
					
					updateBuilder.updateColumnValue("Available_Credit_Amount", new SelectArg(customer.getAvailableCreditAmount()));
					
					if (!StringUtils.isNullOrEmpty(customer.getAddressId())) {
						updateBuilder.updateColumnValue("AddressID", new SelectArg(customer.getAddressId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getBillingCity())) {
						updateBuilder.updateColumnValue("BillingCity", new SelectArg(customer.getBillingCity()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getBillingCountry())) {
						updateBuilder.updateColumnValue("BillingCountry", new SelectArg(customer.getBillingCountry()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getBillingPostalCode())) {
						updateBuilder.updateColumnValue("BillingPostalCode", new SelectArg(customer.getBillingPostalCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getBillingState())) {
						updateBuilder.updateColumnValue("BillingState", new SelectArg(customer.getBillingState()));
					}
					
					updateBuilder.updateColumnValue("Credit_Limit__c", new SelectArg(customer.getCreditLimit()));
					
					if (!StringUtils.isNullOrEmpty(customer.getCurrencyIsoCode())) {
						updateBuilder.updateColumnValue("CurrencyIsoCode", new SelectArg(customer.getCurrencyIsoCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getDescription())) {
						updateBuilder.updateColumnValue("Description", new SelectArg(customer.getDescription()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getEmail()) && StringUtils.isValidEmailAddress(customer.getEmail())) {
						updateBuilder.updateColumnValue("Email__c", new SelectArg(customer.getEmail()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getErpId())) {
						updateBuilder.updateColumnValue("ERP_ID__c", new SelectArg(customer.getErpId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getGroupType())) {
						updateBuilder.updateColumnValue("Group_Type__c", new SelectArg(customer.getGroupType()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getSalesforceId())) {
						updateBuilder.updateColumnValue("SalesForceId", new SelectArg(customer.getSalesforceId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getName())) {
						updateBuilder.updateColumnValue("Name", new SelectArg(customer.getName()));
					}
					
					updateBuilder.updateColumnValue("Outstanding_Balance__c", new SelectArg(customer.getOutstandingBalance()));
					
					if (!StringUtils.isNullOrEmpty(customer.getOwnerId())) {
						updateBuilder.updateColumnValue("OwnerId", new SelectArg(customer.getOwnerId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getParentId())) {
						updateBuilder.updateColumnValue("ParentId", new SelectArg(customer.getParentId()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getPaymentDeliveryConsolidation())) {
						updateBuilder.updateColumnValue("Payment_Delivery_Consolidation__c", new SelectArg(customer.getPaymentDeliveryConsolidation()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getPaymentTerms())) {
						updateBuilder.updateColumnValue("Payment_Terms__c", new SelectArg(customer.getPaymentTerms()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getPhone())) {
						updateBuilder.updateColumnValue("Phone", new SelectArg(customer.getPhone()));
					}
					
					updateBuilder.updateColumnValue("Prepaid_Amount__c", customer.getPrepaidAmount());
					
					updateBuilder.updateColumnValue("Pull_from_SAP__c", customer.isPullFromSap());
					
					updateBuilder.updateColumnValue("Push_to_SAP__c", customer.isPushToSap());
					
					if (!StringUtils.isNullOrEmpty(customer.getShippingCity())) {
						updateBuilder.updateColumnValue("ShippingCity", new SelectArg(customer.getShippingCity()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getShippingCountry())) {
						updateBuilder.updateColumnValue("ShippingCountry", new SelectArg(customer.getShippingCountry()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getShippingPostalCode())) {
						updateBuilder.updateColumnValue("ShippingPostalCode", new SelectArg(customer.getShippingPostalCode()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getShippingState())) {
						updateBuilder.updateColumnValue("ShippingState", new SelectArg(customer.getShippingState()));
					}
					
					if (!StringUtils.isNullOrEmpty(customer.getWebsite())) {
						updateBuilder.updateColumnValue("Website", new SelectArg(customer.getWebsite()));
					}
					
					if (isUsingErpIdColumn) {
						updateBuilder.where().eq("ERP_ID__c", new SelectArg(customer.getErpId()));
					} else {
						updateBuilder.where().eq("SalesForceId", new SelectArg(customer.getSalesforceId()));
					}
					
					updateBuilder.prepare();
					
					updateBuilder.update();
					
					QueryBuilder<Customer, Integer> queryBuilder = dao.queryBuilder();
					
					Where<Customer, Integer> where = queryBuilder.where();
					
					if (isUsingErpIdColumn) {
						where = where.eq("ERP_ID__c", new SelectArg(customer.getErpId()));
					} else {
						where = where.eq("SalesForceId", new SelectArg(customer.getSalesforceId()));
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
