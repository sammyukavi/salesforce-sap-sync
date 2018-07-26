package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.PushCustomerContacts;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.CONTACTS;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncContactsDataService extends BaseDataService<PushCustomerContacts, SyncRestService> {
	
	@Override
	protected Class<SyncRestService> getRestServiceClass() {
		return SyncRestService.class;
	}
	
	public void pushContactsToServer(PushCustomerContacts contacts, GetCallback<PushCustomerContacts> callback) {
		executeSingleTask(callback, restService.postContacts(
				"Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), CONTACTS,
				contacts));
	}
}
