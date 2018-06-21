package ke.co.blueconsulting.sianroses.data.impl;

import com.google.gson.Gson;
import ke.co.blueconsulting.sianroses.data.rest.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.retrofit.SyncRestService;
import ke.co.blueconsulting.sianroses.model.ServerResponse;

import java.util.HashMap;
import java.util.Map;

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
    Gson gson = new Gson();
    Map<String, String> request = new HashMap<>();
    request.put("data", gson.toJson(serverResponse.getData()));
    request.put("name", "sammy");
    //executeSingleTask(callback, restService.post("ATTEMPT-POSTING-TOKEN-HERE", request));
    executeSingleTask(callback, restService.post(serverResponse));
  }
  
}
