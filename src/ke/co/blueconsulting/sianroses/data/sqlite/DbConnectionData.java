package ke.co.blueconsulting.sianroses.data.sqlite;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.io.File;
import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.SyncDashboard.CONSTANTS.APP_DIR_NAME;

public class DbConnectionData {
  
  private final static String DATABASE_NAME = "production.db";
  private Dao<DbConnection, Integer> dbConnectionDao;
  
  
  public DbConnectionData() throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    ConnectionSource connectionSource = new JdbcConnectionSource(getDatabaseUrl());
    dbConnectionDao = DaoManager.createDao(connectionSource, DbConnection.class);
    TableUtils.createTableIfNotExists(connectionSource, DbConnection.class);
  }
  
  private String getDatabaseUrl() {
    String separator = File.separator;
    String directoryName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
    File directory = new File(directoryName);
    if (!directory.exists()) {
      directory.mkdirs();
    }
    return "jdbc:sqlite:" + directoryName + DATABASE_NAME;
  }
  
  
  public void save(DbConnection dbConnection) throws SQLException {
    dbConnection.setId(1);
    dbConnectionDao.createOrUpdate(dbConnection);
  }
  
  public DbConnection getConnectionData() throws SQLException {
    return dbConnectionDao.queryForId(1);
  }
}
