package ke.co.blueconsulting.sianroses.data.rest;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import ke.co.blueconsulting.sianroses.data.impl.SqliteDbService;
import ke.co.blueconsulting.sianroses.model.DbUser;

import java.sql.SQLException;

public class BaseDbService {
  
  protected JdbcConnectionSource connectionSource;
  
  public BaseDbService() {
    try {
      connectionSource = new JdbcConnectionSource(getConnectionUrl());
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  private String getConnectionUrl() throws SQLException, ClassNotFoundException {
    SqliteDbService sqliteDbService = new SqliteDbService();
    DbUser connectionData = sqliteDbService.getConnectionData();
    return "jdbc:sqlserver://" + connectionData.getServerAddress() + ":" + connectionData.getServerPort()
        + ";" + "databaseName=" + connectionData.getDatabaseName() + ";user=" + connectionData.getDatabaseUsername() +
        ";password=" + connectionData.getDatabasePassword();
  }
  
  public <S> Dao<S, Integer> createDao(Class<S> sClass) throws SQLException {
    return DaoManager.createDao(connectionSource, sClass);
  }
  
}
