package ke.co.blueconsulting.sianroses.presenter;


import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.mssql.DbManager;
import ke.co.blueconsulting.sianroses.data.sqlite.DbConnectionData;
import ke.co.blueconsulting.sianroses.model.DbUser;

import java.sql.SQLException;

public class SyncPresenter implements SyncContract.Presenter {
  private SyncContract.View syncDashboard;
  private DbConnectionData dbConnectionData;
  private Thread connectThread;
  private DbManager dbManager;
  
  public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
    this.syncDashboard = syncDashboard;
    this.dbConnectionData = new DbConnectionData();
    this.dbManager = new DbManager();
  }
  
  @Override
  public void testConnection(String serverAddress, String serverPort, String databaseName,
                             String databaseUsername, String databasePassword) {
    syncDashboard.setIsBusy(true);
    connectThread = new Thread(() -> {
      try {
        dbManager.testOrmLiteConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword);
      } catch (SQLException e) {
        syncDashboard.showError(e.getLocalizedMessage());
      } finally {
        try {
          syncDashboard.setIsBusy(false);
          connectThread.join();
        } catch (Exception ignored) {
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
      DbUser dbUser = new DbUser();
      dbUser.setServerAddress(serverAddress);
      dbUser.setServerPort(Integer.parseInt(serverPort));
      dbUser.setDatabaseName(databaseName);
      dbUser.setDatabaseUsername(databaseUsername);
      dbUser.setDatabasePassword(databasePassword);
      dbUser.setSyncPeriod(Integer.parseInt(syncPeriod));
      dbUser.setSyncPeriodUnit(syncPeriodUnit);
      dbConnectionData.save(dbUser);
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  @Override
  public void getDbConnectionData() throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      DbUser connectionData = dbConnectionData.getConnectionData();
      if (connectionData != null) {
        syncDashboard.updateUiFields(connectionData);
      }
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
}
