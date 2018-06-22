package ke.co.blueconsulting.sianroses.presenter;


import com.sun.istack.internal.NotNull;
import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.impl.SqliteDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDbService;
import ke.co.blueconsulting.sianroses.data.rest.DataService;
import ke.co.blueconsulting.sianroses.model.ArCredit;
import ke.co.blueconsulting.sianroses.model.DbUser;
import ke.co.blueconsulting.sianroses.model.ServerResponse;
import ke.co.blueconsulting.sianroses.util.Console;

import java.sql.SQLException;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_CONNECTION_SUCCESSFUL;

public class SyncPresenter implements SyncContract.Presenter {
  private SyncContract.View syncDashboard;
  private SqliteDbService sqliteDbService;
  private Thread connectThread;
  private SyncDataService syncDataService;
  private SyncDbService syncDbService;
  
  
  public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
    this.syncDashboard = syncDashboard;
    this.sqliteDbService = new SqliteDbService();
    this.syncDataService = new SyncDataService();
    this.syncDbService = new SyncDbService();
  }
  
  @Override
  public void testDbConnection(@NotNull String serverAddress, String serverPort, String databaseName,
                               String databaseUsername, String databasePassword) {
    final boolean[] connectionSuccessful = {false};
    syncDashboard.setIsBusy(true);
    connectThread = new Thread(() -> {
      try {
        if (syncDbService.testServerConnection(serverAddress, serverPort, databaseName, databaseUsername, databasePassword)) {
          connectionSuccessful[0] = true;
        }
      } catch (ClassNotFoundException | SQLException e) {
        syncDashboard.showErrorMessage(e.getLocalizedMessage());
      } finally {
        syncDashboard.setIsBusy(false);
        if (connectionSuccessful[0]) {
          syncDashboard.showSuccessMessage(SyncDashboard.getString(MESSAGE_CONNECTION_SUCCESSFUL));
        }
        try {
          connectThread.join();
        } catch (Exception ignored) {
        }
      }
    });
    connectThread.start();
  }
  
  @Override
  public void performSync() throws SQLException {
    //fetchFromTheServer()
    sendToTheServer();
  }
  
  private void sendToTheServer() throws SQLException {
    DataService.GetCallback<ServerResponse> postToserverCallback = new DataService.GetCallback<ServerResponse>() {
      @Override
      public void onCompleted(ServerResponse serverResponse) {
        Console.dump(serverResponse);
      }
      
      @Override
      public void onError(Throwable t) {
        t.printStackTrace();
      }
    };
    
    List<ArCredit> arCredits = syncDbService.getUnsyncedRecords(ArCredit.class);
    ServerResponse serverResponse = new ServerResponse();
    serverResponse.addData("ArCredit", arCredits);
    syncDataService.postToServer(serverResponse, postToserverCallback);
  }
  
  private void fetchFromTheServer() {
    DataService.GetCallback<ServerResponse> getFromTheServerCallback = new DataService.GetCallback<ServerResponse>() {
      @Override
      public void onCompleted(ServerResponse serverResponse) {
        System.out.println(serverResponse.toString());
      }
      
      @Override
      public void onError(Throwable t) {
        t.printStackTrace();
      }
    };
    syncDataService.getFromServer(getFromTheServerCallback);
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
      sqliteDbService.save(dbUser);
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  @Override
  public void getDbConnectionData() throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      DbUser connectionData = sqliteDbService.getConnectionData();
      if (connectionData != null) {
        syncDashboard.updateUiFields(connectionData);
      }
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  @Override
  public void testSalesforceAuthentication(String salesforceClientId, String salesforceClientSecret, String salesforceUsername, String salesforcePassword, String salesforceSecurityToken) {
    
    DataService.GetCallback<ServerResponse> authCallback = new DataService.GetCallback<ServerResponse>() {
      @Override
      public void onCompleted(ServerResponse serverResponse) {
      
      }
      
      @Override
      public void onError(Throwable t) {
        t.printStackTrace();
      }
    };
    
    
    syncDashboard.setIsBusy(true);
    connectThread = new Thread(() -> {
      try {
        syncDataService.authenticate(salesforceClientId, salesforceClientSecret, salesforceUsername, salesforcePassword, salesforceSecurityToken, authCallback);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        syncDashboard.setIsBusy(false);
        try {
          connectThread.join();
        } catch (Exception ignored) {
        }
      }
    });
    connectThread.start();
    
    
  }
}
