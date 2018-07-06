package ke.co.blueconsulting.sianroses.model.salesforce;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "CUSTOMERCONTACTS")
public class CustomerContacts {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@DatabaseField(columnName = "CONTACTID")
	private String contactId;
	
	@DatabaseField(columnName = "AccountID")
	private String accountId;
	
	@DatabaseField(columnName = "Lastname")
	private String lastName;
	
	@DatabaseField(columnName = "Firstname")
	private String firstName;
	
	@DatabaseField(columnName = "Title")
	private String title;
	
	@DatabaseField(columnName = "Mobile")
	private String mobile;
	
	@DatabaseField(columnName = "Phone")
	private String phone;
	
	@DatabaseField(columnName = "Fax")
	private String fax;
	
	@DatabaseField(columnName = "Email")
	private String email;
	
	@DatabaseField(columnName = "BIRTHDATE")
	private Date birthDate;
	
	@DatabaseField(columnName = "Department")
	private String department;
	
	@DatabaseField(columnName = "MailingStreet")
	private Date mailingStreet;
	
	@DatabaseField(columnName = "MailingCity")
	private Date mailingCity;
	
	@DatabaseField(columnName = "MailingState")
	private Date mailingState;
	
	@DatabaseField(columnName = "MailingPostalCode")
	private Date mailingPostalCode;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	private boolean pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	private boolean pullFromSap;
	
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getContactId() {
		return contactId;
	}
	
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getFax() {
		return fax;
	}
	
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public Date getMailingStreet() {
		return mailingStreet;
	}
	
	public void setMailingStreet(Date mailingStreet) {
		this.mailingStreet = mailingStreet;
	}
	
	public Date getMailingCity() {
		return mailingCity;
	}
	
	public void setMailingCity(Date mailingCity) {
		this.mailingCity = mailingCity;
	}
	
	public Date getMailingState() {
		return mailingState;
	}
	
	public void setMailingState(Date mailingState) {
		this.mailingState = mailingState;
	}
	
	public Date getMailingPostalCode() {
		return mailingPostalCode;
	}
	
	public void setMailingPostalCode(Date mailingPostalCode) {
		this.mailingPostalCode = mailingPostalCode;
	}
	
}
