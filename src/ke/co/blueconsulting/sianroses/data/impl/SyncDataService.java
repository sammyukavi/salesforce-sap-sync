package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.db.SyncDbService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.ServerResponse;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.GRANT_TYPE_PASSWORD;

public class SyncDataService extends BaseDataService<ServerResponse, SyncRestService, SyncDbService> {
  
  @Override
  protected Class<SyncRestService> getRestServiceClass() {
    return SyncRestService.class;
  }
  
  @Override
  protected SyncDbService getDbServiceInstanceClass() {
    return new SyncDbService();
  }
  
  public void getFromServer(GetCallback<ServerResponse> callback) {
    executeSingleTask(callback, restService.fetch("Attempt-Fetching-Token-here"));
  }
  
  public void postToServer(ServerResponse serverResponse, GetCallback<ServerResponse> callback) {
    executeSingleTask(callback, restService.post(serverResponse));
  }
  
  public void authenticate(String salesforceClientId, String salesforceClientSecret,
                           String salesforceUsername, String salesforcePassword, String salesforceSecurityToken,
                           GetCallback<ServerResponse> callback) {
    executeSingleTask(callback, restService.authenticate(salesforceClientId, salesforceClientSecret,
        GRANT_TYPE_PASSWORD, salesforceUsername, salesforcePassword + salesforceSecurityToken));
  }
}
