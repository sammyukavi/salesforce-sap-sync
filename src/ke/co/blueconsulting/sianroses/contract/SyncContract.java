package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.sql.SQLException;

public class SyncContract {
  
  public interface View {
    
    void showError(String message);
    
    void showError(String title, String message);
    
    void setIsBusy(boolean isBusy);
    
    void updateUiFields(DbConnection dbConnectionData);
  }
  
  public interface Presenter {
    
    void testConnection(String serverAddress, String serverPort, String databaseName,
                        String databaseUsername, String databasePassword) throws InterruptedException;
    
    void sync();
    
    void saveConnectionDetails(String serverAddress, String serverPort, String databaseName, String databaseUsername,
                               String databasePassword, String syncPeriod, String syncPeriodUnit) throws SQLException;
    
    void getDbConnectionData() throws SQLException;
  }
}
