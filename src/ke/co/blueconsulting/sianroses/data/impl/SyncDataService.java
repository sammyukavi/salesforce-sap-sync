package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.rest.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.retrofit.SyncRestService;
import ke.co.blueconsulting.sianroses.model.ServerResponse;

public class SyncDataService extends BaseDataService<ServerResponse, SyncRestService, SyncDbService> {
  
  @Override
  protected Class<SyncRestService> getRestServiceClass() {
    return SyncRestService.class;
  }
  
  @Override
  protected SyncDbService getDbServiceInstanceClass() {
    return new SyncDbService();
  }
  
  public void getServerData(GetCallback<ServerResponse> callback) {
    executeSingleTask(callback, restService.getServerData("Token here"));
  }
  
}
