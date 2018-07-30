package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.util.Console;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRODUCTS;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncProductDataService extends BaseDataService<Response, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void pushProductToServer(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.postProducts(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), PRODUCTS,
				response));
	}
}
