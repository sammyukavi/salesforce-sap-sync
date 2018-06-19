package ke.co.blueconsulting.sianroses.presenter;


import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.mssql.DaoManager;
import ke.co.blueconsulting.sianroses.data.sqlite.DbConnectionData;
import ke.co.blueconsulting.sianroses.model.DbConnection;

import java.sql.SQLException;

public class SyncPresenter implements SyncContract.Presenter {
  private SyncContract.View syncDashboard;
  private DbConnectionData dbConnectionData;
  private Thread connectThread;
  private DaoManager daoManager;
  
  public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
    this.syncDashboard = syncDashboard;
    this.dbConnectionData = new DbConnectionData();
    this.daoManager = new DaoManager();
  }
  
  @Override
  public void testConnection(String serverAddress, String serverPort, String databaseName,
                             String databaseUsername, String databasePassword) {
    syncDashboard.setIsBusy(true);
    connectThread = new Thread(() -> {
      try {
        daoManager.testConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword);
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
        syncDashboard.showError(e.getLocalizedMessage());
      } finally {
        try {
          syncDashboard.setIsBusy(false);
          connectThread.join();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    });
    connectThread.start();
  }
  
  @Override
  public void sync() {
  
  }
  
  @Override
  public void saveConnectionDetails(String serverAddress, String serverPort, String databaseName,
                                    String databaseUsername, String databasePassword, String syncPeriod,
                                    String syncPeriodUnit) throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      DbConnection dbConnection = new DbConnection();
      dbConnection.setServerAddress(serverAddress);
      dbConnection.setServerPort(Integer.parseInt(serverPort));
      dbConnection.setDatabaseName(databaseName);
      dbConnection.setDatabaseUsername(databaseUsername);
      dbConnection.setDatabasePassword(databasePassword);
      dbConnection.setSyncPeriod(Integer.parseInt(syncPeriod));
      dbConnection.setSyncPeriodUnit(syncPeriodUnit);
      dbConnectionData.save(dbConnection);
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  @Override
  public void getDbConnectionData() throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      DbConnection connectionData = dbConnectionData.getConnectionData();
      if (connectionData != null) {
        syncDashboard.updateUiFields(connectionData);
      }
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
}
