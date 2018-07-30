package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.app.Response;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRICElIST;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncPriceListDataService extends BaseDataService<Response, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	
	public void pushPriceListToServer(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.postPriceList(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), PRICElIST,
				response));
	}
}
