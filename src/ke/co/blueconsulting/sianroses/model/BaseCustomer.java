package ke.co.blueconsulting.sianroses.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class BaseCustomer implements Serializable {

    @DatabaseField(columnName = "ERP_ID__c")
    private String erpId;

    @DatabaseField(columnName = "Name")
    private String name;

    @SerializedName("CurrencyIsoCode")
    @DatabaseField(columnName = "Currencyisocode")
    private String currencyIsoCode;

    @DatabaseField(columnName = "Account_Number__c")
    private String accountNumber;

    @SerializedName("BillingCity")
    @DatabaseField(columnName = "Billingcity")
    private String billingCity;

    @DatabaseField(columnName = "billingState")
    private String billingState;

    @SerializedName("BillingPostalCode")
    @DatabaseField(columnName = "Billingpostalcode")
    private String billingPostalCode;

    @SerializedName("BillingCountry")
    @DatabaseField(columnName = "Billingcountry")
    private String billingCountry;

    @SerializedName("ShippingCity")
    @DatabaseField(columnName = "Shippingcity")
    private String shippingCity;

    @SerializedName("ShippingState")
    @DatabaseField(columnName = "Shippingstate")
    private String shippingState;

    @SerializedName("ShippingPostalCode")
    @DatabaseField(columnName = "Shippingpostalcode")
    private String shippingPostalCode;

    @SerializedName("ShippingCountry")
    @DatabaseField(columnName = "Shippingcountry")
    private String shippingcountry;

    @SerializedName("Phone")
    @DatabaseField(columnName = "Phone")
    private String phone;

    @SerializedName("Email__c")
    @DatabaseField(columnName = "Email__c")
    private String email;

    @SerializedName("Website")
    @DatabaseField(columnName = "Website")
    private String Website;

    @SerializedName("OwnerId")
    @DatabaseField(columnName = "Ownerid")
    private String Ownerid;

    @DatabaseField(columnName = "Description")
    private String Description;

    @SerializedName("Group_Type__c")
    @DatabaseField(columnName = "Group_Type__c")
    private String groupType;

    @SerializedName("Active__c")
    @DatabaseField(columnName = "Active__c")
    private String active;

    @SerializedName("Account_Payment_Terms__c")
    @DatabaseField(columnName = "Account_Payment_Terms__c")
    private String accountPaymentTerms;

    @SerializedName("Payment_Terms__c")
    @DatabaseField(columnName = "Payment_Terms__c")
    private String paymentTerms;

    @SerializedName("Credit_Limit__c")
    @DatabaseField(columnName = "Credit_Limit__c")
    private double creditLimit;

    @SerializedName("Prepaid_Amount__c")
    @DatabaseField(columnName = "Prepaid_Amount__c")
    private double prepaidAmount;

    @DatabaseField(columnName = "Outstanding_Balance__c")
    private double outstandingBalance;

    @DatabaseField(columnName = "A_R_Account__c")
    private String arAccount;

    @DatabaseField(columnName = "Payment_Delivery_Consolidation__c")
    private String paymentDeliveryConsolidation;

    @DatabaseField(columnName = "Parentid")
    private String parentid;

    @DatabaseField(columnName = "Available_Credit_Amount")
    private double availableCreditAmount;
}
