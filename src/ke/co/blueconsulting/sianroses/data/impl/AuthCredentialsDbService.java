package ke.co.blueconsulting.sianroses.data.impl;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.app.AuthCredentials;

import java.io.File;
import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.SQLITE_DATABASE_NAME;

public class AuthCredentialsDbService {
  
  private Dao<AuthCredentials, Integer> credentialsDao;
  private AuthCredentials authCredentials;
  
  
  public AuthCredentialsDbService() throws ClassNotFoundException, SQLException {
    Class.forName("org.sqlite.JDBC");
    ConnectionSource connectionSource = new JdbcConnectionSource(getDatabaseUrl());
    credentialsDao = DaoManager.createDao(connectionSource, AuthCredentials.class);
    TableUtils.createTableIfNotExists(connectionSource, AuthCredentials.class);
    this.authCredentials = credentialsDao.queryForId(1);
    if (authCredentials == null) {
      authCredentials = new AuthCredentials();
    }
  }
  
  private String getDatabaseUrl() {
    String separator = File.separator;
    String directoryName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
    File directory = new File(directoryName);
    if (!directory.exists()) {
      directory.mkdirs();
    }
    return "jdbc:sqlite:" + directoryName + SQLITE_DATABASE_NAME;
  }
  
  public void save(AuthCredentials authCredentials) throws SQLException {
    authCredentials.setId(1);
    credentialsDao.createOrUpdate(authCredentials);
  }
  
  public AuthCredentials getAuthCredentials() {
    return authCredentials;
  }
  
  public void setAuthCredentials(AuthCredentials authCredentials) {
    this.authCredentials = authCredentials;
  }
}
