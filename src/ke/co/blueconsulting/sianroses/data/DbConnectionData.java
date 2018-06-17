package ke.co.blueconsulting.sianroses.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.io.File;

import static ke.co.blueconsulting.sianroses.MainApplication.CONSTANTS.APP_DIR_NAME;

public class DbConnectionData {
  
  private final static String DATABASE_NAME = "production.db";
  private Dao<DbConnection, Integer> dbConnectionDao;
  private ConnectionSource connectionSource = null;
  public DbConnection dbConnectionData;
  
  public DbConnectionData() {
    try {
      Class.forName("org.sqlite.JDBC");
      this.connectionSource = new JdbcConnectionSource(getDatabaseUrl());
      setupDatabase();
    } catch (Exception e) {
      e.printStackTrace();
    }
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
  
  
  private void setupDatabase() {
    try {
      dbConnectionDao = DaoManager.createDao(connectionSource, DbConnection.class);
      TableUtils.createTableIfNotExists(connectionSource, DbConnection.class);
      dbConnectionData = dbConnectionDao.queryForId(1);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (dbConnectionData == null) {
        dbConnectionData = new DbConnection();
      }
    }
  }
  
  
  public DbConnection getDbConnectionData() {
    return dbConnectionData;
  }
}
