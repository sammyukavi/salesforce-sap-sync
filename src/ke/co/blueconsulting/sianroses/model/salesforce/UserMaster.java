package ke.co.blueconsulting.sianroses.model.salesforce;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This model maps SAP user accounts to Salesforce user accounts
 * Syncing for user accounts is a one way push from SAP to Salesforce.
 */
@DatabaseTable(tableName = "USERSMASTER")
public class UserMaster {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@DatabaseField(columnName = "User_Code__c")
	private String userCode;
	
	@DatabaseField(columnName = "username")
	private String username;
	
	@DatabaseField(columnName = "ID")
	private String id;
	
	@DatabaseField(columnName = "lname")
	private String lname;
	
	@DatabaseField(columnName = "fname")
	private String fname;
	
	@DatabaseField(columnName = "Email")
	private String email;
	
	@DatabaseField(columnName = "Phone")
	private String phone;
	
	@DatabaseField(columnName = "isActive")
	private String isActive;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	private Character pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	private Character pullFromSap;
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getFname() {
		return fname;
	}
	
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getIsActive() {
		return isActive;
	}
	
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
