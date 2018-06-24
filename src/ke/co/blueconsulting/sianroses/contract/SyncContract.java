package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.model.app.AuthCredentials;

import java.sql.SQLException;

public class SyncContract {
  
  public interface View {
    
    void showErrorMessage(String message);
    
    void showErrorMessage(String title, String message);
    
    void setIsBusy(boolean isBusy);
    
    void updateUiFields(AuthCredentials authCredentialsData);
    
    void showSuccessMessage(String message);
  }
  
  public interface Presenter {
    
    void testDbConnection(String serverAddress, String serverPort, String databaseName,
                          String databaseUsername, String databasePassword) throws InterruptedException;
    
    void performSync() throws SQLException;
    
    void saveConnectionDetails(String serverAddress, String serverPort, String databaseName, String databaseUsername,
                               String databasePassword, String syncPeriod, String syncPeriodUnit) throws SQLException;
    
    void getCredentials() throws SQLException;
    
    void testSalesforceAuthentication(String salesforceClientId, String salesforceClientSecret, String salesforceUsername,
                                      String salesforcePassword, String salesforceSecurityToken);
  }
}
