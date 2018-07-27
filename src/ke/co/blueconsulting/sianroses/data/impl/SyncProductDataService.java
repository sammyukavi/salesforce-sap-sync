package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.PushProduct;
import ke.co.blueconsulting.sianroses.util.Console;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRODUCTS;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncProductDataService extends BaseDataService<PushProduct, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void pushProductToServer(PushProduct priceList, GetCallback<PushProduct> callback) {
		executeSingleTask(callback, restService.postProduct(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), PRODUCTS,
				priceList));
	}
}
