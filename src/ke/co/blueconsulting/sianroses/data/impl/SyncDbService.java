package ke.co.blueconsulting.sianroses.data.impl;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;
import ke.co.blueconsulting.sianroses.model.salesforce.ArCredit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SyncDbService extends BaseDbService {
  
  
  public SyncDbService() {
    super();
  }
  
  public boolean testServerConnection(String serverAddress, String serverPort, String databaseName,
                                      String databaseUsername, String databasePassword) throws ClassNotFoundException, SQLException {
    String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName=" +
        databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
    Connection connection = null;
    boolean status;
    try {
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      connection = DriverManager.getConnection(connectionUrl);
      status = true;
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (Exception ignored) {
        }
      }
    }
    return status;
  }
  
  
  public void attempt_save() {
    try {
      Dao<ArCredit, Integer> dao = createDao(ArCredit.class);
      dao.create(new ArCredit());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  
  }
  
  
  public <S> List<S> getUnsyncedRecords(Class<S> sClass) throws SQLException {
    
    String columnName = "Push_to_SAP__c";
    Dao<S, Integer> dao = createDao(sClass);
    QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
    Where<S, Integer> where = queryBuilder.where();
    
    List<S> accountList =
        dao.query(where.or(where.isNull(columnName), where.ne(columnName, "pushed"))
            .prepare());
    return accountList;
  }
}

