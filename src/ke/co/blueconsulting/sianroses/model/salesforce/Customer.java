package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps Salesforce accounts to SAP user accounts Syncing for user
 * accounts is two way.
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
    private boolean activeC;

    @DatabaseField(columnName = "Available_Credit_Amount")
    @SerializedName("Available_Credit_Amount__c")
    @Expose
    private double availableCreditAmount;

    @DatabaseField(columnName = "AddressID")
    @SerializedName("AddressID__c")
    @Expose
    private double addressIdC;

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

    public String getaRAccountC() {
        return aRAccountC;
    }

    public void setaRAccountC(String aRAccountC) {
        this.aRAccountC = aRAccountC;
    }

    public String getAccountNumberC() {
        return accountNumberC;
    }

    public void setAccountNumberC(String accountNumberC) {
        this.accountNumberC = accountNumberC;
    }

    public String getAccountPaymentTermsC() {
        return accountPaymentTermsC;
    }

    public void setAccountPaymentTermsC(String accountPaymentTermsC) {
        this.accountPaymentTermsC = accountPaymentTermsC;
    }

    public boolean isActiveC() {
        return activeC;
    }

    public void setActiveC(boolean activeC) {
        this.activeC = activeC;
    }

    public double getAvailableCreditAmount() {
        return availableCreditAmount;
    }

    public void setAvailableCreditAmount(double availableCreditAmount) {
        this.availableCreditAmount = availableCreditAmount;
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

    public double getCreditLimitC() {
        return creditLimitC;
    }

    public void setCreditLimitC(double creditLimitC) {
        this.creditLimitC = creditLimitC;
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

    public String getEmailC() {
        return emailC;
    }

    public void setEmailC(String emailC) {
        this.emailC = emailC;
    }

    public int geteRPIdC() {
        return eRPIdC;
    }

    public void seteRPIdC(int eRPIdC) {
        this.eRPIdC = eRPIdC;
    }

    public String getGroupTypeC() {
        return groupTypeC;
    }

    public void setGroupTypeC(String groupTypeC) {
        this.groupTypeC = groupTypeC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOutstandingBalanceC() {
        return outstandingBalanceC;
    }

    public void setOutstandingBalanceC(double outstandingBalanceC) {
        this.outstandingBalanceC = outstandingBalanceC;
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

    public String getPaymentDeliveryConsolidationC() {
        return paymentDeliveryConsolidationC;
    }

    public void setPaymentDeliveryConsolidationC(String paymentDeliveryConsolidationC) {
        this.paymentDeliveryConsolidationC = paymentDeliveryConsolidationC;
    }

    public String getPaymentTermsC() {
        return paymentTermsC;
    }

    public void setPaymentTermsC(String paymentTermsC) {
        this.paymentTermsC = paymentTermsC;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPrepaidAmountC() {
        return prepaidAmountC;
    }

    public void setPrepaidAmountC(double prepaidAmountC) {
        this.prepaidAmountC = prepaidAmountC;
    }

    public boolean isPullFromSAPC() {
        return pullFromSAPC;
    }

    public void setPullFromSAPC(boolean pullFromSAPC) {
        this.pullFromSAPC = pullFromSAPC;
    }

    public boolean isPushToSAPC() {
        return pushToSAPC;
    }

    public void setPushToSAPC(boolean pushToSAPC) {
        this.pushToSAPC = pushToSAPC;
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

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }
}
