package ke.co.blueconsulting.sianroses.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.io.IOException;

public class CustomerAdapter extends TypeAdapter<Customer> {
	
	@Override
	public void write(JsonWriter jsonWriter, Customer customer) throws IOException {
		jsonWriter.beginObject();
		
		if (!StringUtils.isNullOrEmpty(customer.getaRAccountC())) {
			jsonWriter.name("A_R_Account__c");
			jsonWriter.value(customer.getaRAccountC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getAccountNumber())) {
			jsonWriter.name("AccountNumber");
			jsonWriter.value(customer.getAccountNumber());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getAccountPaymentTermsC())) {
			jsonWriter.name("Account_Payment_Terms__c");
			jsonWriter.value(customer.getAccountPaymentTermsC());
		}
		
		
		jsonWriter.name("Active__c");
		jsonWriter.value(customer.getActiveC());
		
		
		jsonWriter.name("Available_Credit_Amount__c");
		jsonWriter.value(customer.getAvailableCreditAmount());
		
		
		if (!StringUtils.isNullOrEmpty(customer.getAddressIdC())) {
			jsonWriter.name("AddressID__c");
			jsonWriter.value(customer.getAddressIdC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getBillingCity())) {
			jsonWriter.name("BillingCity");
			jsonWriter.value(customer.getBillingCity());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getBillingCountry())) {
			jsonWriter.name("BillingCountry");
			jsonWriter.value(customer.getBillingCountry());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getBillingPostalCode())) {
			jsonWriter.name("BillingPostalCode");
			jsonWriter.value(customer.getBillingPostalCode());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getBillingState())) {
			jsonWriter.name("BillingState");
			jsonWriter.value(customer.getBillingState());
		}
		
		
		jsonWriter.name("Credit_Limit__c");
		jsonWriter.value(customer.getCreditLimitC());
		
		
		/**
		 * TODO When you fix the commented lines below. Also fix those in CustomerDbService for updating customers
		 */
		
		if (!StringUtils.isNullOrEmpty(customer.getCurrencyIsoCode())) {
			//jsonWriter.name("CurrencyIsoCode");
			jsonWriter.name("Currency__c");
			jsonWriter.value(customer.getCurrencyIsoCode());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getDescription())) {
			jsonWriter.name("Description");
			jsonWriter.value(customer.getDescription());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getEmailC()) && StringUtils.isValidEmailAddress(customer.getEmailC())) {
			jsonWriter.name("Email__c");
			jsonWriter.value(customer.getEmailC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.geteRPIdC())) {
			jsonWriter.name("ERP_ID__c");
			jsonWriter.value(customer.geteRPIdC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getGroupTypeC())) {
			jsonWriter.name("Group_Type__c");
			jsonWriter.value(customer.getGroupTypeC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getSalesforceId())) {
			jsonWriter.name("Id");
			jsonWriter.value(customer.getSalesforceId());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getName())) {
			jsonWriter.name("Name");
			jsonWriter.value(customer.getName());
		}
		
		
		jsonWriter.name("Outstanding_Balance__c");
		jsonWriter.value(customer.getOutstandingBalanceC());
		
		
		if (!StringUtils.isNullOrEmpty(customer.getOwnerId())) {
			//jsonWriter.name("OwnerId");
			jsonWriter.name("SAP_OwnerId__c");
			jsonWriter.value(customer.getOwnerId());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getParentId())) {
			//jsonWriter.name("ParentId");
			jsonWriter.name("Parent_Id__c");
			jsonWriter.value(customer.getParentId());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getPaymentDeliveryConsolidationC())) {
			jsonWriter.name("Payment_Delivery_Consolidation__c");
			jsonWriter.value(customer.getPaymentDeliveryConsolidationC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getPaymentTermsC())) {
			//jsonWriter.name("Payment_Terms__c");
			jsonWriter.name("Payment_Terms_Code__c");
			jsonWriter.value(customer.getPaymentTermsC());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getPhone())) {
			jsonWriter.name("Phone");
			jsonWriter.value(customer.getPhone());
		}
		
		
		jsonWriter.name("Prepaid_Amount__c");
		jsonWriter.value(customer.getPrepaidAmountC());
		
		
		jsonWriter.name("Pull_from_SAP__c");
		jsonWriter.value(customer.isPullFromSAPC());
		
		jsonWriter.name("Push_to_SAP__c");
		jsonWriter.value(customer.isPushToSAPC());
		
		
		if (!StringUtils.isNullOrEmpty(customer.getShippingCity())) {
			jsonWriter.name("ShippingCity");
			jsonWriter.value(customer.getShippingCity());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getShippingCountry())) {
			jsonWriter.name("ShippingCountry");
			jsonWriter.value(customer.getShippingCountry());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getShippingPostalCode())) {
			jsonWriter.name("ShippingPostalCode");
			jsonWriter.value(customer.getShippingPostalCode());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getShippingState())) {
			jsonWriter.name("ShippingState");
			jsonWriter.value(customer.getShippingState());
		}
		
		if (!StringUtils.isNullOrEmpty(customer.getWebsite())) {
			jsonWriter.name("Website");
			jsonWriter.value(customer.getWebsite());
		}
		
		jsonWriter.endObject();
	}
	
	@Override
	public Customer read(JsonReader jsonReader) {
		Customer customer = new Customer();
		try {
			jsonReader.beginObject();
			String fieldname = null;
			while (jsonReader.hasNext()) {
				JsonToken token = jsonReader.peek();
				if (token.equals(JsonToken.NAME)) {
					fieldname = jsonReader.nextName();
				}
				if ("Id".equals(fieldname)) {
					jsonReader.peek();
					customer.setSalesforceId(jsonReader.nextString());
				} else if ("A_R_Account__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setaRAccountC(jsonReader.nextString());
				} else if ("AccountNumber".equals(fieldname)) {
					jsonReader.peek();
					customer.setAccountNumber(jsonReader.nextString());
				} else if ("Account_Payment_Terms__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setAccountPaymentTermsC(jsonReader.nextString());
				} else if ("Active__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setActiveC(jsonReader.nextBoolean());
				} else if ("Available_Credit_Amount__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setAvailableCreditAmount(jsonReader.nextDouble());
				} else if ("AddressID__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setAddressIdC(jsonReader.nextString());
				} else if ("BillingCity".equals(fieldname)) {
					jsonReader.peek();
					customer.setBillingCity(jsonReader.nextString());
				} else if ("BillingCountry".equals(fieldname)) {
					jsonReader.peek();
					customer.setBillingCountry(jsonReader.nextString());
				} else if ("BillingPostalCode".equals(fieldname)) {
					jsonReader.peek();
					customer.setBillingPostalCode(jsonReader.nextString());
				} else if ("BillingState".equals(fieldname)) {
					jsonReader.peek();
					customer.setBillingState(jsonReader.nextString());
				} else if ("Credit_Limit__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setCreditLimitC(jsonReader.nextDouble());
				} else if ("CurrencyIsoCode".equals(fieldname)) {
					jsonReader.peek();
					customer.setCurrencyIsoCode(jsonReader.nextString());
				} else if ("Description".equals(fieldname)) {
					jsonReader.peek();
					customer.setDescription(jsonReader.nextString());
				} else if ("Email__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setEmailC(jsonReader.nextString());
				} else if ("ERP_ID__c".equals(fieldname)) {
					jsonReader.peek();
					customer.seteRPIdC(jsonReader.nextString());
				} else if ("Group_Type__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setGroupTypeC(jsonReader.nextString());
				} else if ("Name".equals(fieldname)) {
					jsonReader.peek();
					customer.setName(jsonReader.nextString());
				} else if ("Outstanding_Balance__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setOutstandingBalanceC(jsonReader.nextDouble());
				} else if ("OwnerId".equals(fieldname)) {
					jsonReader.peek();
					customer.setOwnerId(jsonReader.nextString());
				} else if ("ParentId".equals(fieldname)) {
					jsonReader.peek();
					customer.setParentId(jsonReader.nextString());
				} else if ("Payment_Delivery_Consolidation__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setPaymentDeliveryConsolidationC(jsonReader.nextString());
				} else if ("Payment_Terms__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setPaymentTermsC(jsonReader.nextString());
				} else if ("Phone".equals(fieldname)) {
					jsonReader.peek();
					customer.setPhone(jsonReader.nextString());
				} else if ("Prepaid_Amount__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setPrepaidAmountC(jsonReader.nextDouble());
				} else if ("Pull_from_SAP__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setPullFromSAPC(jsonReader.nextBoolean());
				} else if ("Push_to_SAP__c".equals(fieldname)) {
					jsonReader.peek();
					customer.setPushToSAPC(jsonReader.nextBoolean());
				} else if ("ShippingCity".equals(fieldname)) {
					jsonReader.peek();
					customer.setShippingCity(jsonReader.nextString());
				} else if ("ShippingCountry".equals(fieldname)) {
					jsonReader.peek();
					customer.setShippingCountry(jsonReader.nextString());
				} else if ("ShippingPostalCode".equals(fieldname)) {
					jsonReader.peek();
					customer.setShippingPostalCode(jsonReader.nextString());
				} else if ("ShippingState".equals(fieldname)) {
					jsonReader.peek();
					customer.setShippingState(jsonReader.nextString());
				} else if ("Website".equals(fieldname)) {
					jsonReader.peek();
					customer.setWebsite(jsonReader.nextString());
				} else {
					jsonReader.skipValue();
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			AppLogger.logError("An error occurred while trying to write a customer object. " + e.getMessage());
		}
		return customer;
	}
	
}
