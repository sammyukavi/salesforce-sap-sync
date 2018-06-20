package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CUSTOMERCONTACTS")
public class Customers {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int id;
  
  @DatabaseField(columnName = "ERP_ID__c")
  private String erpId;
  
  @DatabaseField(columnName = "Name")
  private String name;
  
  @DatabaseField(columnName = "Currencyisocode")
  private String currencyIsoCode;
  
  @DatabaseField(columnName = "Account_Number__c")
  private String accountNumber;
  
  @DatabaseField(columnName = "Billingcity")
  private String billingCity;
  
  @DatabaseField(columnName = "billingState")
  private String billingState;
  
  @DatabaseField(columnName = "Billingpostalcode")
  private String billingPostalCode;
  
  @DatabaseField(columnName = "Billingcountry")
  private String billingCountry;
  
  @DatabaseField(columnName = "Shippingcity")
  private String shippingCity;
  
  @DatabaseField(columnName = "Shippingstate")
  private String shippingState;
  
  @DatabaseField(columnName = "Shippingostalcode")
  private String shippingPostalCode;
  
  @DatabaseField(columnName = "Shippingcountry")
  private String shippingcountry;
  
  @DatabaseField(columnName = "Phone")
  private String phone;
  
  @DatabaseField(columnName = "Email_c")
  private String email;
  
  @DatabaseField(columnName = "Website")
  private String Website;
  
  @DatabaseField(columnName = "Ownerid")
  private String Ownerid;
  
  @DatabaseField(columnName = "Description")
  private String Description;
  
  @DatabaseField(columnName = "Group_Type__c")
  private String groupType;
  
  @DatabaseField(columnName = "Active__c")
  private String active;
  
  @DatabaseField(columnName = "Account_Payment_Terms__c")
  private String accountPaymentTerms;
  
  @DatabaseField(columnName = "Payment_Terms__c")
  private String paymentTerms;
  
  @DatabaseField(columnName = "Credit_Limit__c")
  private double creditLimit;
  
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
  
  @DatabaseField(columnName = "Push_to_SAP__c")
  private Character pushToSap;
  
  @DatabaseField(columnName = "Pull_from_SAP__c")
  private Character pullFromSap;
  
}
