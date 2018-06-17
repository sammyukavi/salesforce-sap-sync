package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "db_connections")
public class DbConnection {
  
  public static final String DB_SERVER_ADDRESS = "server_address";
  public static final String DB_SERVER_PORT = "server_port";
  public static final String DB_NAME = "db_name";
  public static final String DB_USERNAME = "db_username";
  public static final String DB_PASSWORD = "db_password";
  public static final String DB_SYNC_VALUE = "sync_value";
  public static final String DB_SYNC_UNIT = "sync_unit";
  
  @DatabaseField(generatedId = true)
  private int id;
  
  @DatabaseField(columnName = DB_SERVER_ADDRESS, canBeNull = false)
  private String serverAddress = "";
  
  @DatabaseField(columnName = DB_SERVER_PORT, canBeNull = false)
  private int serverPort;
  
  @DatabaseField(columnName = DB_NAME, canBeNull = false)
  private String databaseName = "";
  
  @DatabaseField(columnName = DB_USERNAME, canBeNull = false)
  private String databaseUsername = "";
  
  @DatabaseField(columnName = DB_PASSWORD, canBeNull = false)
  private String databasePassword = "";
  
  @DatabaseField(columnName = DB_SYNC_VALUE, canBeNull = false)
  private int syncValue;
  
  @DatabaseField(columnName = DB_SYNC_UNIT, canBeNull = false)
  private String syncUnit = "";
  
  public DbConnection() {
  
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
  
  public int getSyncValue() {
    return syncValue;
  }
  
  public void setSyncValue(int syncValue) {
    this.syncValue = syncValue;
  }
  
  public String getSyncUnit() {
    return syncUnit;
  }
  
  public void setSyncUnit(String syncUnit) {
    this.syncUnit = syncUnit;
  }
}
