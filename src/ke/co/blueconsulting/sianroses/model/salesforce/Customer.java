package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps Salesforce accounts to SAP user accounts.
 * Syncing for user accounts is two way.
 */
@DatabaseTable(tableName = "CUSTOMERS")
public class Customer implements Serializable {

    @DatabaseField(columnName = "A_R_Account__c")
    @SerializedName("A_R_Account__c")
    @Expose
    private String aRAccountC;
	
	@DatabaseField(columnName = "Account_Number__c")
	@SerializedName("Account_Number__c")
	@Expose
	private String accountNumberC;
	
	@DatabaseField(columnName = "Account_Payment_Terms__c")
	@SerializedName("Account_Payment_Terms__c")
	@Expose
	private String accountPaymentTermsC;
	
	@DatabaseField(columnName = "Active__c")
	@SerializedName("Active__c")
	@Expose
	private String activeC;
	
	@DatabaseField(columnName = "Available_Credit_Amount")
	@SerializedName("Available_Credit_Amount__c")
	@Expose
	private double availableCreditAmount;
	
	@DatabaseField(columnName = "AddressID")
	@SerializedName("AddressID__c")
	@Expose
	private String addressIdC;
	
	@DatabaseField(columnName = "BillingCity")
	@SerializedName("BillingCity")
	@Expose
	private String billingCity;
	
	@DatabaseField(columnName = "BillingCountry")
	@SerializedName("BillingCountry")
	@Expose
	private String billingCountry;
	
	@DatabaseField(columnName = "BillingPostalCode")
	@SerializedName("BillingPostalCode")
	@Expose
	private String billingPostalCode;
	
	@DatabaseField(columnName = "BillingState")
	@SerializedName("BillingState")
	@Expose
	private String billingState;
	
	@DatabaseField(columnName = "Credit_Limit__c")
	@SerializedName("Credit_Limit__c")
	@Expose
	private double creditLimitC;
	
	@DatabaseField(columnName = "CurrencyIsoCode")
	@SerializedName("CurrencyIsoCode")
	@Expose
	private String currencyIsoCode;
	
	@DatabaseField(columnName = "Description")
	@SerializedName("Description")
	@Expose
	private String description;
	
	@DatabaseField(columnName = "Email__c")
	@SerializedName("Email__c")
	@Expose
	private String emailC;
	
	@DatabaseField(columnName = "AUTOID", generatedId = true)
	@SerializedName("ERP_ID__c")
	@Expose
	private int eRPIdC;
	
	@DatabaseField(columnName = "Group_Type__c")
	@SerializedName("Group_Type__c")
	@Expose
	private String groupTypeC;
	
	@SerializedName("Id")
	@Expose
	private String salesforceId;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Outstanding_Balance__c")
	@SerializedName("Outstanding_Balance__c")
	@Expose
	private double outstandingBalanceC;
	
	@DatabaseField(columnName = "OwnerId")
	@SerializedName("OwnerId")
	@Expose
	private String ownerId;
	
	@DatabaseField(columnName = "ParentId")
	@SerializedName("ParentId")
	@Expose
	private String ParentId;
	
	@DatabaseField(columnName = "Payment_Delivery_Consolidation__c")
	@SerializedName("Payment_Delivery_Consolidation__c")
	@Expose
	private String paymentDeliveryConsolidationC;
	
	@DatabaseField(columnName = "Payment_Terms__c")
	@SerializedName("Payment_Terms__c")
	@Expose
	private String paymentTermsC;
	
	@DatabaseField(columnName = "Phone")
	@SerializedName("Phone")
	@Expose
	private String phone;
	
	@DatabaseField(columnName = "Prepaid_Amount__c")
	@SerializedName("Prepaid_Amount__c")
	@Expose
	private double prepaidAmountC;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC;
	
	@DatabaseField(columnName = "ShippingCity")
	@SerializedName("ShippingCity")
	@Expose
	private String shippingCity;
	
	@DatabaseField(columnName = "ShippingCountry")
	@SerializedName("ShippingCountry")
	@Expose
	private String shippingCountry;
	
	@DatabaseField(columnName = "ShippingPostalCode")
	@SerializedName("ShippingPostalCode")
	@Expose
	private String shippingPostalCode;
	
	@DatabaseField(columnName = "ShippingState")
	@SerializedName("ShippingState")
	@Expose
	private String shippingState;
	
	@DatabaseField(columnName = "Website")
	@SerializedName("Website")
	@Expose
	private String website;
	
	public boolean isPushToSAPC() {
		return pushToSAPC;
	}
	
	public void setPushToSAPC(boolean pushToSAPC) {
		this.pushToSAPC = pushToSAPC;
	}
 
}
