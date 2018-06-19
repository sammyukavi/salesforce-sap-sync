package ke.co.blueconsulting.sianroses.data.mssql;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import ke.co.blueconsulting.sianroses.data.sqlite.DbConnectionData;
import ke.co.blueconsulting.sianroses.model.DbUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
  
  private final JdbcConnectionSource connectionSource;
  
  public DbManager() throws SQLException, ClassNotFoundException {
    connectionSource = new JdbcConnectionSource(getConnectionUrl());
  }
  
  public <S> Dao<S, Integer> createDao(Class<S> sClass) throws SQLException {
    return DaoManager.createDao(connectionSource, sClass);
  }
  
  private String getConnectionUrl() throws SQLException, ClassNotFoundException {
    DbConnectionData dbConnectionData = new DbConnectionData();
    DbUser connectionData = dbConnectionData.getConnectionData();
    return "jdbc:sqlserver://" + connectionData.getServerAddress() + ":" + connectionData.getServerPort()
        + ";" + "databaseName=" + connectionData.getDatabaseName() + ";user=" + connectionData.getDatabaseUsername() +
        ";password=" + connectionData.getDatabasePassword();
  }
  
  public boolean testConnection(String serverAddress, String serverPort, String databaseName,
                                String databaseUsername, String databasePassword) throws ClassNotFoundException, SQLException {
    String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName=" +
        databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
    Connection con = null;
    boolean status;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      con = DriverManager.getConnection(connectionUrl);
      status = true;
    } finally {
      if (con != null) {
        try {
          con.close();
        } catch (Exception ignored) {
        }
      }
    }
    return status;
  }
  
  
}
