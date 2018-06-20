package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.model.DbUser;

import java.sql.SQLException;

public class SyncContract {
  
  public interface View {
    
    void showErrorMessage(String message);
    
    void showErrorMessage(String title, String message);
    
    void setIsBusy(boolean isBusy);
    
    void updateUiFields(DbUser dbUserData);
    
    void showSuccessMessage(String message);
  }
  
  public interface Presenter {
    
    void testConnection(String serverAddress, String serverPort, String databaseName,
                        String databaseUsername, String databasePassword) throws InterruptedException;
    
    void sync() throws SQLException;
    
    void saveConnectionDetails(String serverAddress, String serverPort, String databaseName, String databaseUsername,
                               String databasePassword, String syncPeriod, String syncPeriodUnit) throws SQLException;
    
    void getDbConnectionData() throws SQLException;
  }
}
