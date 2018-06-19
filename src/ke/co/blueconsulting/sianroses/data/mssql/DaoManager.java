package ke.co.blueconsulting.sianroses.data.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoManager {
  
  public void testConnection(String serverAddress, String serverPort, String databaseName,
                             String databaseUsername, String databasePassword) throws ClassNotFoundException, SQLException {
    String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName=" +
        databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
    Connection con = null;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      con = DriverManager.getConnection(connectionUrl);
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (Exception ignored) {
        }
      }
    }
  }
}
