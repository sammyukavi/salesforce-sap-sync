package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.BasePresenter;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DbConnectionData;
import ke.co.blueconsulting.sianroses.fragment.SyncFragment;
import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.sql.SQLException;

public class SyncPresenter extends BasePresenter implements SyncContract.Presenter {
  private SyncContract.View syncFragment;
  private DbConnectionData dbConnectionData;
  
  public SyncPresenter(SyncFragment syncFragment) throws SQLException, ClassNotFoundException {
    this.syncFragment = syncFragment;
    this.dbConnectionData = new DbConnectionData();
  }
  
  @Override
  public void testConnection() {
    syncFragment.setIsBusy(true);
    /*
    //syncFragment.showMessage("Connection Failed", ERROR);
	  //http://41.72.195.70
	  
	// Create a variable for the connection string.  
      String connectionUrl = "jdbc:sqlserver://41.72.195.70:1443;" +  
         "databaseName=AdventureWorks;user=UserName;password=*****";  

      // Declare the JDBC objects.  
      Connection con = null;  
      Statement stmt = null;  
      ResultSet rs = null;  

      try {  
         // Establish the connection.  
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
         con = DriverManager.getConnection(connectionUrl);  

         // Create and execute an SQL statement that returns some data.  
         String SQL = "SELECT TOP 10 * FROM Person.Contact";  
         stmt = con.createStatement();  
         rs = stmt.executeQuery(SQL);  

         // Iterate through the data in the result set and display it.  
         while (rs.next()) {  
            System.out.println(rs.getString(4) + " " + rs.getString(6));  
         }  
      }  

      // Handle any errors that may have occurred.  
      catch (Exception e) {  
         e.printStackTrace();  
      }  
      finally {  
         if (rs != null) try { rs.close(); } catch(Exception e) {}  
         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
         if (con != null) try { con.close(); } catch(Exception e) {}  
      }  	*/
    
  }
  
  @Override
  public void sync() {
  
  }
  
  @Override
  public void saveConnectionDetails(String serverAddress, String serverPort, String databaseName,
                                    String databaseUsername, String databasePassword, String syncPeriod,
                                    String syncPeriodUnit) throws SQLException {
    syncFragment.setIsBusy(true);
    DbConnection dbConnection = new DbConnection();
    dbConnection.setServerAddress(serverAddress);
    dbConnection.setServerPort(Integer.parseInt(serverPort));
    dbConnection.setDatabaseName(databaseName);
    dbConnection.setDatabaseUsername(databaseUsername);
    dbConnection.setDatabasePassword(databasePassword);
    dbConnection.setSyncPeriod(Integer.parseInt(syncPeriod));
    dbConnection.setSyncPeriodUnit(syncPeriodUnit);
    dbConnectionData.save(dbConnection);
    syncFragment.setIsBusy(false);
  }
  
  @Override
  public void getDbConnectionData() throws SQLException {
    syncFragment.setIsBusy(true);
    DbConnection connectionData = dbConnectionData.getConnectionData();
    if (connectionData != null) {
      syncFragment.updateUiFields(connectionData);
    }
    syncFragment.setIsBusy(false);
  }
}
