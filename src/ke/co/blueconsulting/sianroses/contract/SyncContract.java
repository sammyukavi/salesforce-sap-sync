package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.sql.SQLException;

public class SyncContract {
  
  public interface View {
    
    void showMessage(String message, int error_code);
    
    void setIsBusy(boolean isBusy);
    
    void updateUiFields(DbConnection dbConnectionData);
  }
  
  public interface Presenter {
    
    void testConnection();
    
    void sync();
    
    void saveConnectionDetails(String serverAddress, String serverPort, String databaseName, String databaseUsername,
                               String databasePassword, String syncPeriod, String syncPeriodUnit) throws SQLException;
    
    void getDbConnectionData() throws SQLException;
  }
}
