package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This is the model used for storing the database login details.
 */
@DatabaseTable(tableName = "db_connections")
public class DbUser {
  
  @DatabaseField(generatedId = true)
  private int id;
  
  @DatabaseField(columnName = "server_address", canBeNull = false)
  private String serverAddress = "";
  
  @DatabaseField(columnName = "server_port", canBeNull = false)
  private int serverPort;
  
  @DatabaseField(columnName = "db_name", canBeNull = false)
  private String databaseName = "";
  
  @DatabaseField(columnName = "db_username", canBeNull = false)
  private String databaseUsername = "";
  
  @DatabaseField(columnName = "db_password", canBeNull = false)
  private String databasePassword = "";
  
  @DatabaseField(columnName = "sync_period", canBeNull = false)
  private int syncPeriod;
  
  @DatabaseField(columnName = "sync_period_unit", canBeNull = false)
  private String syncPeriodUnit = "";
  
  public DbUser() {
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getServerAddress() {
    return serverAddress;
  }
  
  public void setServerAddress(String serverAddress) {
    this.serverAddress = serverAddress;
  }
  
  public int getServerPort() {
    return serverPort;
  }
  
  public void setServerPort(int serverPort) {
    this.serverPort = serverPort;
  }
  
  public String getDatabaseName() {
    return databaseName;
  }
  
  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }
  
  public String getDatabaseUsername() {
    return databaseUsername;
  }
  
  public void setDatabaseUsername(String databaseUsername) {
    this.databaseUsername = databaseUsername;
  }
  
  public String getDatabasePassword() {
    return databasePassword;
  }
  
  public void setDatabasePassword(String databasePassword) {
    this.databasePassword = databasePassword;
  }
  
  public int getSyncPeriod() {
    return syncPeriod;
  }
  
  public void setSyncPeriod(int syncPeriod) {
    this.syncPeriod = syncPeriod;
  }
  
  public String getSyncPeriodUnit() {
    return syncPeriodUnit;
  }
  
  public void setSyncPeriodUnit(String syncPeriodUnit) {
    this.syncPeriodUnit = syncPeriodUnit;
  }
}
