package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "CUSTOMERS")
public class Customer {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int id;
  
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
  
  @DatabaseField(columnName = "Push_to_SAP__c")
  private Character pushToSap;
  
  @DatabaseField(columnName = "Pull_from_SAP__c")
  private Character pullFromSap;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getErpId() {
    return erpId;
  }
  
  public void setErpId(String erpId) {
    this.erpId = erpId;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getCurrencyIsoCode() {
    return currencyIsoCode;
  }
  
  public void setCurrencyIsoCode(String currencyIsoCode) {
    this.currencyIsoCode = currencyIsoCode;
  }
  
  public String getAccountNumber() {
    return accountNumber;
  }
  
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }
  
  public String getBillingCity() {
    return billingCity;
  }
  
  public void setBillingCity(String billingCity) {
    this.billingCity = billingCity;
  }
  
  public String getBillingState() {
    return billingState;
  }
  
  public void setBillingState(String billingState) {
    this.billingState = billingState;
  }
  
  public String getBillingPostalCode() {
    return billingPostalCode;
  }
  
  public void setBillingPostalCode(String billingPostalCode) {
    this.billingPostalCode = billingPostalCode;
  }
  
  public String getBillingCountry() {
    return billingCountry;
  }
  
  public void setBillingCountry(String billingCountry) {
    this.billingCountry = billingCountry;
  }
  
  public String getShippingCity() {
    return shippingCity;
  }
  
  public void setShippingCity(String shippingCity) {
    this.shippingCity = shippingCity;
  }
  
  public String getShippingState() {
    return shippingState;
  }
  
  public void setShippingState(String shippingState) {
    this.shippingState = shippingState;
  }
  
  public String getShippingPostalCode() {
    return shippingPostalCode;
  }
  
  public void setShippingPostalCode(String shippingPostalCode) {
    this.shippingPostalCode = shippingPostalCode;
  }
  
  public String getShippingcountry() {
    return shippingcountry;
  }
  
  public void setShippingcountry(String shippingcountry) {
    this.shippingcountry = shippingcountry;
  }
  
  public String getPhone() {
    return phone;
  }
  
  public void setPhone(String phone) {
    this.phone = phone;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getWebsite() {
    return Website;
  }
  
  public void setWebsite(String website) {
    Website = website;
  }
  
  public String getOwnerid() {
    return Ownerid;
  }
  
  public void setOwnerid(String ownerid) {
    Ownerid = ownerid;
  }
  
  public String getDescription() {
    return Description;
  }
  
  public void setDescription(String description) {
    Description = description;
  }
  
  public String getGroupType() {
    return groupType;
  }
  
  public void setGroupType(String groupType) {
    this.groupType = groupType;
  }
  
  public String getActive() {
    return active;
  }
  
  public void setActive(String active) {
    this.active = active;
  }
  
  public String getAccountPaymentTerms() {
    return accountPaymentTerms;
  }
  
  public void setAccountPaymentTerms(String accountPaymentTerms) {
    this.accountPaymentTerms = accountPaymentTerms;
  }
  
  public String getPaymentTerms() {
    return paymentTerms;
  }
  
  public void setPaymentTerms(String paymentTerms) {
    this.paymentTerms = paymentTerms;
  }
  
  public double getCreditLimit() {
    return creditLimit;
  }
  
  public void setCreditLimit(double creditLimit) {
    this.creditLimit = creditLimit;
  }
  
  public double getPrepaidAmount() {
    return prepaidAmount;
  }
  
  public void setPrepaidAmount(double prepaidAmount) {
    this.prepaidAmount = prepaidAmount;
  }
  
  public double getOutstandingBalance() {
    return outstandingBalance;
  }
  
  public void setOutstandingBalance(double outstandingBalance) {
    this.outstandingBalance = outstandingBalance;
  }
  
  public String getArAccount() {
    return arAccount;
  }
  
  public void setArAccount(String arAccount) {
    this.arAccount = arAccount;
  }
  
  public String getPaymentDeliveryConsolidation() {
    return paymentDeliveryConsolidation;
  }
  
  public void setPaymentDeliveryConsolidation(String paymentDeliveryConsolidation) {
    this.paymentDeliveryConsolidation = paymentDeliveryConsolidation;
  }
  
  public String getParentid() {
    return parentid;
  }
  
  public void setParentid(String parentid) {
    this.parentid = parentid;
  }
  
  public double getAvailableCreditAmount() {
    return availableCreditAmount;
  }
  
  public void setAvailableCreditAmount(double availableCreditAmount) {
    this.availableCreditAmount = availableCreditAmount;
  }
  
  public Character getPushToSap() {
    return pushToSap;
  }
  
  public void setPushToSap(Character pushToSap) {
    this.pushToSap = pushToSap;
  }
  
  public Character getPullFromSap() {
    return pullFromSap;
  }
  
  public void setPullFromSap(Character pullFromSap) {
    this.pullFromSap = pullFromSap;
  }
}
