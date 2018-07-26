package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.PushCustomer;
import ke.co.blueconsulting.sianroses.util.Console;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypes.ACCOUNTS;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class SyncCustomersDataService extends BaseDataService<PushCustomer, SyncRestService> {

    @Override
    protected Class<SyncRestService> getRestServiceClass() {
        return SyncRestService.class;
    }


    public void pushCustomersToServer(PushCustomer customers, GetCallback<PushCustomer> callback) {
    	Console.log("Sent to the server");
		Console.logToJson(customers);
    	executeSingleTask(callback, restService.postCustomers(
                "Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken(), ACCOUNTS,
                customers));
    }
}
