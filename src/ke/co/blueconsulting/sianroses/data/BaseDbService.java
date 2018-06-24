package ke.co.blueconsulting.sianroses.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import ke.co.blueconsulting.sianroses.data.impl.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.model.app.AuthCredentials;

import java.sql.SQLException;

public class BaseDbService {
  
  private JdbcConnectionSource connectionSource;
  
  public BaseDbService() {
    try {
      connectionSource = new JdbcConnectionSource(getConnectionUrl());
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  private String getConnectionUrl() throws SQLException, ClassNotFoundException {
    AuthCredentialsDbService authCredentialsDbService = new AuthCredentialsDbService();
    AuthCredentials connectionData = authCredentialsDbService.getAuthCredentials();
    return "jdbc:sqlserver://" + connectionData.getServerAddress() + ":" + connectionData.getServerPort()
        + ";" + "databaseName=" + connectionData.getDatabaseName() + ";user=" + connectionData.getDatabaseUsername() +
        ";password=" + connectionData.getDatabasePassword();
  }
  
  protected <S> Dao<S, Integer> createDao(Class<S> sClass) throws SQLException {
    return DaoManager.createDao(connectionSource, sClass);
  }
  
}
