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

	private static final long serialVersionUID = 1L;

	@DatabaseField(columnName = "A_R_Account__c")
    @SerializedName("A_R_Account__c")
    @Expose
    private String aRAccountC=null;
	
	@DatabaseField(columnName = "Account_Number__c")
	@SerializedName("AccountNumber")
	@Expose
	private String accountNumber=null;
	
	@DatabaseField(columnName = "Account_Payment_Terms__c")
	@SerializedName("Account_Payment_Terms__c")
	@Expose
	private String accountPaymentTermsC=null;
	
	@DatabaseField(columnName = "Active__c")
	//@SerializedName("Active__c")
	@Expose
	private String activeC=null;
	
	@DatabaseField(columnName = "Available_Credit_Amount")
	@SerializedName("Available_Credit_Amount__c")
	@Expose
	private double availableCreditAmount=0;
	
	@DatabaseField(columnName = "AddressID")
	@SerializedName("AddressID__c")
	@Expose
	private String addressIdC=null;
	
	@DatabaseField(columnName = "BillingCity")
	@SerializedName("BillingCity")
	@Expose
	private String billingCity=null;
	
	@DatabaseField(columnName = "BillingCountry")
	@SerializedName("BillingCountry")
	@Expose
	private String billingCountry=null;
	
	@DatabaseField(columnName = "BillingPostalCode")
	@SerializedName("BillingPostalCode")
	@Expose
	private String billingPostalCode=null;
	
	@DatabaseField(columnName = "BillingState")
	@SerializedName("BillingState")
	@Expose
	private String billingState=null;
	
	@DatabaseField(columnName = "Credit_Limit__c")
	@SerializedName("Credit_Limit__c")
	@Expose
	private double creditLimitC=0;
	
	@DatabaseField(columnName = "CurrencyIsoCode")
	@SerializedName("CurrencyIsoCode")
	@Expose
	private String currencyIsoCode=null;
	
	@DatabaseField(columnName = "Description")
	@SerializedName("Description")
	@Expose
	private String description=null;
	
	@DatabaseField(columnName = "Email__c")
	@SerializedName("Email__c")
	@Expose
	private String emailC=null;
	
	@DatabaseField(columnName = "AUTOID", generatedId = true)
	@SerializedName("ERP_ID__c")
	@Expose
	private int eRPIdC;
	
	@DatabaseField(columnName = "Group_Type__c")
	@SerializedName("Group_Type__c")
	@Expose
	private String groupTypeC=null;
	
	@DatabaseField(columnName = "SalesforceId")
	@SerializedName("Id")
	@Expose
	private String salesforceId=null;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name=null;
	
	@DatabaseField(columnName = "Outstanding_Balance__c")
	@SerializedName("Outstanding_Balance__c")
	@Expose
	private double outstandingBalanceC=0;
	
	@DatabaseField(columnName = "OwnerId")
	@SerializedName("OwnerId")
	@Expose
	private String ownerId=null;
	
	@DatabaseField(columnName = "ParentId")
	@SerializedName("ParentId")
	@Expose
	private String ParentId=null;
	
	@DatabaseField(columnName = "Payment_Delivery_Consolidation__c")
	@SerializedName("Payment_Delivery_Consolidation__c")
	@Expose
	private String paymentDeliveryConsolidationC=null;
	
	@DatabaseField(columnName = "Payment_Terms__c")
	@SerializedName("Payment_Terms__c")
	@Expose
	private String paymentTermsC=null;
	
	@DatabaseField(columnName = "Phone")
	@SerializedName("Phone")
	@Expose
	private String phone=null;
	
	@DatabaseField(columnName = "Prepaid_Amount__c")
	@SerializedName("Prepaid_Amount__c")
	@Expose
	private double prepaidAmountC=0;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC=false;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC=false;
	
	@DatabaseField(columnName = "ShippingCity")
	@SerializedName("ShippingCity")
	@Expose
	private String shippingCity=null;
	
	@DatabaseField(columnName = "ShippingCountry")
	@SerializedName("ShippingCountry")
	@Expose
	private String shippingCountry=null;
	
	@DatabaseField(columnName = "ShippingPostalCode")
	@SerializedName("ShippingPostalCode")
	@Expose
	private String shippingPostalCode=null;
	
	@DatabaseField(columnName = "ShippingState")
	@SerializedName("ShippingState")
	@Expose
	private String shippingState=null;
	
	@DatabaseField(columnName = "Website")
	@SerializedName("Website")
	@Expose
	private String website=null;
	
	public boolean isPushToSAPC() {
		return pushToSAPC;
	}
	
	public void setPushToSAPC(boolean pushToSAPC) {
		this.pushToSAPC = pushToSAPC;
	}
 
}
