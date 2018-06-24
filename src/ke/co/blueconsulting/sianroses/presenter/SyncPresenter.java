package ke.co.blueconsulting.sianroses.presenter;


import com.sun.istack.internal.NotNull;
import ke.co.blueconsulting.sianroses.SyncDashboard;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SyncDbService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.AuthCredentials;
import ke.co.blueconsulting.sianroses.model.salesforce.ArCredit;
import ke.co.blueconsulting.sianroses.model.app.ServerResponse;
import ke.co.blueconsulting.sianroses.util.Console;

import java.sql.SQLException;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.*;

public class SyncPresenter implements SyncContract.Presenter {
  private SyncContract.View syncDashboard;
  private AuthCredentialsDbService authCredentialsDbService;
  private SyncDataService syncDataService;
  private SyncDbService syncDbService;
  private Thread connectThread;
  
  
  public SyncPresenter(SyncDashboard syncDashboard) throws SQLException, ClassNotFoundException {
    this.syncDashboard = syncDashboard;
    this.authCredentialsDbService = new AuthCredentialsDbService();
    this.syncDbService = new SyncDbService();
    createSyncDataService();
  }
  
  private void createSyncDataService() {
    this.syncDataService = new SyncDataService();
  }
  
  @Override
  public void getCredentials() {
    syncDashboard.setIsBusy(true);
    try {
      AuthCredentials connectionData = authCredentialsDbService.getAuthCredentials();
      if (connectionData != null) {
        syncDashboard.updateUiFields(connectionData);
      }
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  /**
   * Saves the user database connections in the sqlite database
   *
   * @param serverAddress
   * @param serverPort
   * @param databaseName
   * @param databaseUsername
   * @param databasePassword
   * @param syncPeriod
   * @param syncPeriodUnit
   * @throws SQLException
   */
  @Override
  public void saveDbAuth(String serverAddress, String serverPort, String databaseName,
                         String databaseUsername, String databasePassword, String syncPeriod,
                         String syncPeriodUnit) throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      AuthCredentials authCredentials = authCredentialsDbService.getAuthCredentials();
      authCredentials.setServerAddress(serverAddress);
      authCredentials.setServerPort(Integer.parseInt(serverPort));
      authCredentials.setDatabaseName(databaseName);
      authCredentials.setDatabaseUsername(databaseUsername);
      authCredentials.setDatabasePassword(databasePassword);
      authCredentials.setSyncPeriod(Integer.parseInt(syncPeriod));
      authCredentials.setSyncPeriodUnit(syncPeriodUnit);
      authCredentialsDbService.save(authCredentials);
    } finally {
      syncDashboard.setIsBusy(false);
    }
  }
  
  /**
   * Saves the salesforce authentication details in the sqlite database
   *
   * @param salesforceClientId
   * @param salesforceClientSecret
   * @param salesforceUsername
   * @param salesforcePassword
   * @param salesforceSecurityToken
   * @throws SQLException
   */
  @Override
  public void saveSalesforceAuth(String salesforceClientId, String salesforceClientSecret,
                                 String salesforceUsername, String salesforcePassword,
                                 String salesforceSecurityToken) throws SQLException {
    syncDashboard.setIsBusy(true);
    try {
      AuthCredentials authCredentials = authCredentialsDbService.getAuthCredentials();
      authCredentials.setSalesforceClientId(salesforceClientId);
      authCredentials.setSalesforceClientSecret(salesforceClientSecret);
      authCredentials.setSalesforceUsername(salesforceUsername);
      authCredentials.setsalesforcePassword(salesforcePassword);
      authCredentials.setsalesforceSecurityToken(salesforceSecurityToken);
      authCredentialsDbService.save(authCredentials);
    } finally {
      syncDashboard.setIsBusy(false);
    }
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
        syncDashboard.setIsBusy(false);
        syncDashboard.showErrorMessage(e.getMessage());
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
    //sendToTheServer();
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
  public void testSalesforceAuth(String salesforceClientId, String salesforceClientSecret,
                                 String salesforceUsername, String salesforcePassword,
                                 String salesforceSecurityToken) {
    
    DataService.GetCallback<ServerResponse> authCallback = new DataService.GetCallback<ServerResponse>() {
      @Override
      public void onCompleted(ServerResponse serverResponse) {
        Console.dump(serverResponse);
        syncDashboard.showSuccessMessage(SyncDashboard.getString(MESSAGE_CONNECTION_SUCCESSFUL));
      }
      
      @Override
      public void onError(Throwable t) {
        syncDashboard.showErrorMessage(SyncDashboard.getString(MESSAGE_LOGIN_FAILED),
            SyncDashboard.getString(MESSAGE_INVALID_SALESFORCE_CREDENTIALS));
      }
    };
    
    syncDashboard.setIsBusy(true);
    connectThread = new Thread(() -> {
      try {
        RestServiceBuilder.switchToSalesforceAuthUrl();
        createSyncDataService();
        syncDataService.authenticate(salesforceClientId, salesforceClientSecret, salesforceUsername, salesforcePassword,
            salesforceSecurityToken, authCallback);
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        RestServiceBuilder.switchToSalesforceBaseUrl();
        createSyncDataService();
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
