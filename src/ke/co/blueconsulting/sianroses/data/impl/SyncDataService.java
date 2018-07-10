package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Result;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncDataService extends BaseDataService<Result, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void getFromServer(GetCallback<Result> callback) {
		executeSingleTask(callback, restService.fetch(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken()));
	}
	
	public void postToServer(Result result, GetCallback<Result> callback) {
		executeSingleTask(callback, restService.post(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), result));
	}
}
