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
}
