package ke.co.blueconsulting.sianroses.model.salesforce;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps SAP user accounts to Salesforce user accounts Syncing for
 * user accounts is a one way push from SAP to Salesforce.
 */
@DatabaseTable(tableName = "SALESEMPLOYEE")
public class SalesEmployee implements Serializable {

    @DatabaseField(generatedId = true, columnName = "AUTOID")
    private int autoId;

    @DatabaseField(columnName = "User_Code__c")
    private String userCode;

    @DatabaseField(columnName = "User_Name__c")
    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
