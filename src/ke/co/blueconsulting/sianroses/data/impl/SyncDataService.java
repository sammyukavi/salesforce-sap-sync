package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.ServerResponse;

public class SyncDataService extends BaseDataService<ServerResponse, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void getFromServer(GetCallback<ServerResponse> callback) {
		executeSingleTask(callback, restService.fetch(authCredentialsDbService.getAppAuthCredentials().getSalesforceSecurityToken()));
	}
	
	public void postToServer(ServerResponse serverResponse, GetCallback<ServerResponse> callback) {
		executeSingleTask(callback, restService.post(serverResponse));
	}
}
