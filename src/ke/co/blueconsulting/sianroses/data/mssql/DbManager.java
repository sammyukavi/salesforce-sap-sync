package ke.co.blueconsulting.sianroses.data.mssql;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.TestDbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
  
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
  
  public void testOrmLiteConnection(String serverAddress, String serverPort, String databaseName,
                                    String databaseUsername, String databasePassword) throws SQLException {
    String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName=" +
        databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
    JdbcConnectionSource connectionSource = new JdbcConnectionSource(connectionUrl);
    Dao<TestDbConnection, Integer> testDao = DaoManager.createDao(connectionSource, TestDbConnection.class);
    TableUtils.createTableIfNotExists(connectionSource, TestDbConnection.class);
    TableUtils.dropTable(connectionSource, TestDbConnection.class, false);
  }
}
