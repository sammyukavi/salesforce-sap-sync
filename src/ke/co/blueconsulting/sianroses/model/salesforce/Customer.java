
package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;

public class Customer {
	
	@SerializedName("Id")
	private String id;
	
	@SerializedName("ERP_ID__c")
	private String erpId;
	
	@SerializedName("Name")
	private String name;
	
	@SerializedName("Currencyisocode")
	private String currencyIsoCode;
	
	@SerializedName("Account_Number__c")
	private String accountNumber;
	
	@SerializedName("Billingcity")
	private String billingCity;
	
	@SerializedName("billingState")
	private String billingState;
	
	@SerializedName("Billingpostalcode")
	private String billingPostalCode;
	
	@SerializedName("Billingcountry")
	private String billingCountry;
	
	@SerializedName("Shippingcity")
	private String shippingCity;
	
	@SerializedName("Shippingstate")
	private String Shippingstate;
	
	@SerializedName("Shippingpostalcode")
	private String shippingPostalCode;
	
	@SerializedName("Shippingcountry")
	private String Shippingcountry;
	
	@SerializedName("Phone")
	private String phone;
	
	@SerializedName("Email__c")
	private String email;
	
	@SerializedName("Website")
	private String Website;
	
	@SerializedName("Ownerid")
	private String Ownerid;
	
	@SerializedName("Description")
	private String Description;
	
	@SerializedName("Group_Type__c")
	private String groupType;
	
	@SerializedName("Active__c")
	private String active;
	
	@SerializedName("Account_Payment_Terms__c")
	@SerializedName("Account_Payment_Terms__c")
	private String accountPaymentTerms;
	
	@SerializedName("Payment_Terms__c")
	@SerializedName("Payment_Terms__c")
	private String paymentTerms;
	
	@SerializedName("Credit_Limit__c")
	@SerializedName("Credit_Limit__c")
	private double creditLimit;
	
	@SerializedName("Prepaid_Amount__c")
	@SerializedName("Prepaid_Amount__c")
	private double prepaidAmount;
	
	@SerializedName("Outstanding_Balance__c")
	private double outstandingBalance;
	
	@SerializedName("A_R_Account__c")
	private String arAccount;
	
	@SerializedName("Payment_Delivery_Consolidation__c")
	private String paymentDeliveryConsolidation;
	
	@SerializedName("Parentid")
	private String parentid;
	
	@SerializedName("Available_Credit_Amount")
	private double availableCreditAmount;
	
	@SerializedName("Push_to_SAP__c")
	private boolean pushToSap;
	
	@SerializedName("Pull_from_SAP__c")
	private boolean pullFromSap;
	
	@SerializedName("Active__c")
	private Boolean activeC;
	
	@SerializedName("Credit_Limit__c")
	private Long creditLimitC;
	
	@SerializedName("Email__c")
	private String emailC;
	
	@SerializedName("OwnerId")
	private String ownerId;
	
	@SerializedName("Prepaid_Amount__c")
	private Long prepaidAmountC;
	
	@SerializedName("Pull_from_SAP__c")
	private Boolean pullFromSAPC;
	
	public Boolean getActiveC() {
		return activeC;
	}
	
	public Long getCreditLimitC() {
		return creditLimitC;
	}
	
	public String getCurrencyIsoCode() {
		return currencyIsoCode;
	}
	
	public String getEmailC() {
		return emailC;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Long getPrepaidAmountC() {
		return prepaidAmountC;
	}
	
	public Boolean getPullFromSAPC() {
		return pullFromSAPC;
	}
	
	public static class Builder {
		
		private Boolean activeC;
		private Long creditLimitC;
		private String currencyIsoCode;
		private String emailC;
		private String id;
		private String name;
		private String ownerId;
		private String phone;
		private Long prepaidAmountC;
		private Boolean pullFromSAPC;
		
		public Customer.Builder withActiveC(Boolean activeC) {
			this.activeC = activeC;
			return this;
		}
		
		public Customer.Builder withCreditLimitC(Long creditLimitC) {
			this.creditLimitC = creditLimitC;
			return this;
		}
		
		public Customer.Builder withCurrencyIsoCode(String currencyIsoCode) {
			this.currencyIsoCode = currencyIsoCode;
			return this;
		}
		
		public Customer.Builder withEmailC(String emailC) {
			this.emailC = emailC;
			return this;
		}
		
		public Customer.Builder withId(String id) {
			this.id = id;
			return this;
		}
		
		public Customer.Builder withName(String name) {
			this.name = name;
			return this;
		}
		
		public Customer.Builder withOwnerId(String ownerId) {
			this.ownerId = ownerId;
			return this;
		}
		
		public Customer.Builder withPhone(String phone) {
			this.phone = phone;
			return this;
		}
		
		public Customer.Builder withPrepaidAmountC(Long prepaidAmountC) {
			this.prepaidAmountC = prepaidAmountC;
			return this;
		}
		
		public Customer.Builder withPullFromSAPC(Boolean pullFromSAPC) {
			this.pullFromSAPC = pullFromSAPC;
			return this;
		}
		
		public Customer build() {
			Customer customer = new Customer();
			customer.activeC = activeC;
			customer.creditLimitC = creditLimitC;
			customer.currencyIsoCode = currencyIsoCode;
			customer.emailC = emailC;
			customer.id = id;
			customer.name = name;
			customer.ownerId = ownerId;
			customer.phone = phone;
			customer.prepaidAmountC = prepaidAmountC;
			customer.pullFromSAPC = pullFromSAPC;
			return customer;
		}
		
	}
	
}
