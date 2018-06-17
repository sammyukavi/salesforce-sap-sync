package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.BasePresenter;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.fragment.SyncFragment;

public class SyncPresenter extends BasePresenter implements SyncContract.Presenter {
  private SyncContract.View syncFragment;
  
  public SyncPresenter(SyncFragment syncFragment) {
    this.syncFragment = syncFragment;
  }
  
  @Override
  public void testConnection() {
    syncFragment.showStatus(true);
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
}
