package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseRestDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Response;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.*;
import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.BEARER;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncDataService extends BaseRestDataService<Response, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	private String getToken() {
		return BEARER + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken();
	}
	
	public void getCustomers(GetCallback<Response> callback) {
		executeSingleTask(callback, restService.fetch(getToken(), ACCOUNTS));
	}
	
	public void pushCustomersToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), ACCOUNTS, response));
	}
	
	public void getContacts(GetCallback<Response> callback) {
		executeSingleTask(callback, restService.fetch(getToken(), CONTACTS));
	}
	
	public void pushCustomersContactsToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), CONTACTS, response));
	}
	
	public void pushPriceListToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), PRICE_LIST, response));
	}
	
	public void pushProductsToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), PRODUCTS, response));
	}
	
	public void pushProductsChildrenToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), PRODUCTS_CHILDREN, response));
	}
	
	public void pushWarehousesToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), WAREHOUSES, response));
	}
	
	public void getPackingLists(GetCallback<Response> callback) {
		executeSingleTask(callback, restService.fetch(getToken(), PACKING_LISTS));
	}
	
	public void pushPackingListsToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), PACKING_LISTS, response));
	}
	
	public void pushStockToSalesforce(Response response, GetCallback<Response> callback) {
		executeSingleTask(callback, restService.post(getToken(), STOCK, response));
	}
}
