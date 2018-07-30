package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Response;
import ke.co.blueconsulting.sianroses.util.Console;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRODUCTS;
import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRODUCTSCHILDREN;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncProductChildrenDataService extends BaseDataService<Response, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void pushProductsChildrenToServer(Response productsChildren, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.postProductChildren(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), PRODUCTSCHILDREN,
				productsChildren));
	}
}
