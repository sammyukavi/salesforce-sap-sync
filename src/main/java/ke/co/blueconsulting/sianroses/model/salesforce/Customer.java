package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

/**
 * This model maps Salesforce accounts to SAP user accounts.
 * Syncing for user accounts is two way.
 */
@DatabaseTable(tableName = "CUSTOMERS")
public class Customer extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 1966939659263952L;
	
	@DatabaseField(columnName = "A_R_Account__c")
	@SerializedName("A_R_Account__c")
	@Expose
	private String aRAccountC;
	
	@DatabaseField(columnName = "Account_Number__c")
	@SerializedName("AccountNumber")
	@Expose
	private String accountNumber;
	
	@DatabaseField(columnName = "Account_Payment_Terms__c")
	@SerializedName("Account_Payment_Terms__c")
	@Expose
	private String accountPaymentTerms;
	
	@DatabaseField(columnName = "Active__c")
	@SerializedName("Active__c")
	@Expose
	private boolean active;
	
	@DatabaseField(columnName = "Available_Credit_Amount")
	@SerializedName("Available_Credit_Amount__c")
	@Expose
	private double availableCreditAmount;
	
	@DatabaseField(columnName = "AddressID")
	@SerializedName("AddressID__c")
	@Expose
	private String addressId;
	
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
	private double creditLimit;
	
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
	private String email;
	
	@DatabaseField(columnName = "ERP_ID__c")
	@SerializedName("ERP_ID__c")
	@Expose
	private String eRPId;
	
	@DatabaseField(columnName = "Group_Type__c")
	@SerializedName("Group_Type__c")
	@Expose
	private String groupType;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Outstanding_Balance__c")
	@SerializedName("Outstanding_Balance__c")
	@Expose
	private double outstandingBalance;
	
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
	private String paymentDeliveryConsolidation;
	
	@DatabaseField(columnName = "Payment_Terms__c")
	@SerializedName("Payment_Terms__c")
	@Expose
	private String paymentTerms;
	
	@DatabaseField(columnName = "Phone")
	@SerializedName("Phone")
	@Expose
	private String phone;
	
	@DatabaseField(columnName = "Prepaid_Amount__c")
	@SerializedName("Prepaid_Amount__c")
	@Expose
	private double prepaidAmount;
	
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
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getaRAccountC() {
		return aRAccountC;
	}
	
	public void setaRAccountC(String aRAccountC) {
		this.aRAccountC = aRAccountC;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getAccountPaymentTerms() {
		return accountPaymentTerms;
	}
	
	public void setAccountPaymentTerms(String accountPaymentTerms) {
		this.accountPaymentTerms = accountPaymentTerms;
	}
	
	public boolean getActiveC() {
		return active;
	}
	
	public double getAvailableCreditAmount() {
		return availableCreditAmount;
	}
	
	public void setAvailableCreditAmount(double availableCreditAmount) {
		this.availableCreditAmount = availableCreditAmount;
	}
	
	public String getAddressId() {
		return addressId;
	}
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public String getBillingCity() {
		return billingCity;
	}
	
	public void setBillingCity(String billingCity) {
		this.billingCity = billingCity;
	}
	
	public String getBillingCountry() {
		return billingCountry;
	}
	
	public void setBillingCountry(String billingCountry) {
		this.billingCountry = billingCountry;
	}
	
	public String getBillingPostalCode() {
		return billingPostalCode;
	}
	
	public void setBillingPostalCode(String billingPostalCode) {
		this.billingPostalCode = billingPostalCode;
	}
	
	public String getBillingState() {
		return billingState;
	}
	
	public void setBillingState(String billingState) {
		this.billingState = billingState;
	}
	
	public double getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}
	
	public void setCurrencyIsoCode(String currencyIsoCode) {
		this.currencyIsoCode = currencyIsoCode;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getErpId() {
		return eRPId;
	}
	
	public void setErpId(String eRPId) {
		this.eRPId = eRPId;
	}
	
	public String getGroupType() {
		return groupType;
	}
	
	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}
	
	public double getOutstandingBalance() {
		return outstandingBalance;
	}
	
	public void setOutstandingBalance(double outstandingBalance) {
		this.outstandingBalance = outstandingBalance;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getParentId() {
		return ParentId;
	}
	
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	
	public String getPaymentDeliveryConsolidation() {
		return paymentDeliveryConsolidation;
	}
	
	public void setPaymentDeliveryConsolidation(String paymentDeliveryConsolidation) {
		this.paymentDeliveryConsolidation = paymentDeliveryConsolidation;
	}
	
	public String getPaymentTerms() {
		return paymentTerms;
	}
	
	public void setPaymentTerms(String paymentTerms) {
		this.paymentTerms = paymentTerms;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getPrepaidAmount() {
		return prepaidAmount;
	}
	
	public void setPrepaidAmount(double prepaidAmount) {
		this.prepaidAmount = prepaidAmount;
	}
	
	public String getShippingCity() {
		return shippingCity;
	}
	
	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}
	
	public String getShippingCountry() {
		return shippingCountry;
	}
	
	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}
	
	public String getShippingPostalCode() {
		return shippingPostalCode;
	}
	
	public void setShippingPostalCode(String shippingPostalCode) {
		this.shippingPostalCode = shippingPostalCode;
	}
	
	public String getShippingState() {
		return shippingState;
	}
	
	public void setShippingState(String shippingState) {
		this.shippingState = shippingState;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
}
