package ke.co.blueconsulting.sianroses.model.salesforce;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "FARMMAPPING")
public class FarmMapping {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "Farm_Code")
  private String farmCode;
  
  @DatabaseField(columnName = "SAP_DB_Name")
  private String sapDbName;
  
  public int getAutoId() {
    return autoId;
  }
  
  public void setAutoId(int autoId) {
    this.autoId = autoId;
  }
  
  public String getFarmCode() {
    return farmCode;
  }
  
  public void setFarmCode(String farmCode) {
    this.farmCode = farmCode;
  }
  
  public String getSapDbName() {
    return sapDbName;
  }
  
  public void setSapDbName(String sapDbName) {
    this.sapDbName = sapDbName;
  }
}
