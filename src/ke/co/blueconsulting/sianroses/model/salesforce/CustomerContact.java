package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps Salesforce accounts' contacts to SAP user accounts.
 * Sync for customer contacts is two way.
 */

@DatabaseTable(tableName = "CUSTOMERCONTACTS")
public class CustomerContact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private Long autoId;
	
	@DatabaseField(columnName = "AccountID")
	@SerializedName("AccountId")
	@Expose
	private String accountId;
	
	@DatabaseField(columnName = "Account_Number")
	@SerializedName("Account_Number__c")
	@Expose
	private String accountNumber;
	
	@DatabaseField(columnName = "BIRTHDATE")
	@SerializedName("Birthdate")
	@Expose
	private String birthDate;
	
	@DatabaseField(columnName = "CONTACTID")
	@SerializedName("Id")
	@Expose
	private String contactId;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	private String salesforceId;
	
	@DatabaseField(columnName = "Department")
	@SerializedName("Department")
	@Expose
	private String department;
	
	@DatabaseField(columnName = "Email")
	@SerializedName("Email")
	@Expose
	private String email;
	
	@DatabaseField(columnName = "Fax")
	@SerializedName("Fax")
	@Expose
	private String fax;
	
	@DatabaseField(columnName = "Firstname")
	@SerializedName("FirstName")
	@Expose
	private String firstName;
	
	@DatabaseField(columnName = "Lastname")
	@SerializedName("LastName")
	@Expose
	private String lastName;
	
	@DatabaseField(columnName = "MailingCity")
	@SerializedName("MailingCity")
	@Expose
	private String mailingCity;
	
	@DatabaseField(columnName = "MailingCountry")
	@SerializedName("MailingCountry")
	@Expose
	private String mailingCountry;
	
	@DatabaseField(columnName = "MailingPostalCode")
	@SerializedName("MailingPostalCode")
	@Expose
	private String mailingPostalCode;
	
	@DatabaseField(columnName = "MailingState")
	@SerializedName("MailingState")
	@Expose
	private String mailingState;
	
	@DatabaseField(columnName = "MailingStreet")
	@SerializedName("MailingStreet")
	@Expose
	private String mailingStreet;
	
	@DatabaseField(columnName = "Mobile")
	@SerializedName("MobilePhone")
	@Expose
	private String mobilePhone;
	
	@DatabaseField(columnName = "Phone")
	@SerializedName("Phone")
	@Expose
	private String phone;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSap;
	
	@SerializedName("Title")
	@Expose
	@DatabaseField(columnName = "Title")
	private String title;
	
	public Long getAutoId() {
		return autoId;
	}
	
	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getSalesforceId() {
		return salesforceId;
	}
	
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMailingCity() {
		return mailingCity;
	}
	
	public void setMailingCity(String mailingCity) {
		this.mailingCity = mailingCity;
	}
	
	public String getMailingCountry() {
		return mailingCountry;
	}
	
	public void setMailingCountry(String mailingCountry) {
		this.mailingCountry = mailingCountry;
	}
	
	public String getMailingPostalCode() {
		return mailingPostalCode;
	}
	
	public void setMailingPostalCode(String mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}
	
	public String getMailingState() {
		return mailingState;
	}
	
	public void setMailingState(String mailingState) {
		this.mailingState = mailingState;
	}
	
	public String getMailingStreet() {
		return mailingStreet;
	}
	
	public void setMailingStreet(String mailingStreet) {
		this.mailingStreet = mailingStreet;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}
	
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public boolean isPushToSap() {
		return pushToSap;
	}
	
	public void setPushToSap(boolean pushToSap) {
		this.pushToSap = pushToSap;
	}
	
	public boolean isPullFromSap() {
		return pullFromSap;
	}
	
	public void setPullFromSap(boolean pullFromSap) {
		this.pullFromSap = pullFromSap;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContactId() {
		return contactId;
	}
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
}
