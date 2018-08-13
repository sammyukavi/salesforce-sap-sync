package ke.co.blueconsulting.sianroses.contract;

import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;

import java.sql.SQLException;

public class SyncContract {
	
	public interface View {
		
		void showErrorMessage(String message);
		
		void showErrorMessage(String title, String message);
		
		void setIsBusy(boolean isBusy);
		
		void updateUiFields(AppAuthCredentials appAuthCredentialsData);
		
		void showSuccessMessage(String message);
		
		void showToolTip(String message, int code);
	}
	
	public interface Presenter {
		
		void testDbConnection(String serverAddress, String serverPort, String databaseName,
		                      String databaseUsername, String databasePassword) throws InterruptedException;
		
		void performSync() throws SQLException;
		
		void saveDbAuth(String serverAddress, String serverPort, String databaseName, String databaseUsername,
		                String databasePassword, String syncPeriod, String syncPeriodUnit) throws SQLException;
		
		void getCredentials() throws SQLException;
		
		void testSalesforceAuth(String salesforceClientId, String salesforceClientSecret, String salesforceUsername,
		                        String salesforcePassword, String salesforceSecurityToken);
		
		void saveSalesforceAuth(String salesforceClientId, String salesforceClientSecret, String salesforceUsername,
		                        String salesforcePassword, String salesforceSecurityToken) throws SQLException;
	}
}
