package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * This model maps Salesforce accounts' contacts to SAP user accounts.
 * Sync for customer contacts is two way.
 */
@DatabaseTable(tableName = "CUSTOMERCONTACTS")
public class CustomerContacts implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
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
	private Date birthDate;
	
	@DatabaseField(columnName = "CONTACTID")
	@SerializedName("Id")
	@Expose
	private String contactId;
	
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
	private Date mailingCity;
	
	@DatabaseField(columnName = "MailingCountry")
	@SerializedName("MailingCountry")
	@Expose
	private Date mailingCountry;
	
	@DatabaseField(columnName = "MailingPostalCode")
	@SerializedName("MailingPostalCode")
	@Expose
	private Date mailingPostalCode;
	
	@DatabaseField(columnName = "MailingState")
	@SerializedName("MailingState")
	@Expose
	private Date mailingState;
	
	@DatabaseField(columnName = "MailingStreet")
	@SerializedName("MailingStreet")
	@Expose
	private Date mailingStreet;
	
	@DatabaseField(columnName = "Mobile")
	@SerializedName("mobilePhone")
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
	
	public void setPushToSap(boolean pushToSap) {
		this.pushToSap = pushToSap;
	}
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getContactId() {
		return contactId;
	}
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
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
	
	public Date getMailingCity() {
		return mailingCity;
	}
	
	public void setMailingCity(Date mailingCity) {
		this.mailingCity = mailingCity;
	}
	
	public Date getMailingCountry() {
		return mailingCountry;
	}
	
	public void setMailingCountry(Date mailingCountry) {
		this.mailingCountry = mailingCountry;
	}
	
	public Date getMailingPostalCode() {
		return mailingPostalCode;
	}
	
	public void setMailingPostalCode(Date mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}
	
	public Date getMailingState() {
		return mailingState;
	}
	
	public void setMailingState(Date mailingState) {
		this.mailingState = mailingState;
	}
	
	public Date getMailingStreet() {
		return mailingStreet;
	}
	
	public void setMailingStreet(Date mailingStreet) {
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
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
