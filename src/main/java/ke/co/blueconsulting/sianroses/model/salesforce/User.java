package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

@DatabaseTable(tableName = "USERSMASTER")
public class User extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 1182198467097474049L;
	
	@DatabaseField(columnName = "User_Code__c")
	@SerializedName("User_Code__c")
	@Expose
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}
	
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
}
