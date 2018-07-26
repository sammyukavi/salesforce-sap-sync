package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.SyncRestService;
import ke.co.blueconsulting.sianroses.model.app.Response;

/**
 * A data service that is used to send and receive data from Salesforce
 */
public class FetchDataService extends BaseDataService<Response, SyncRestService> {

    @Override
    protected Class<SyncRestService> getRestServiceClass() {
        return SyncRestService.class;
    }

    public void getFromServer(GetCallback<Response> callback) {
        executeSingleTask(callback, restService.fetch(
                "Bearer " + authCredentialsDbService.getAppAuthCredentials().getSalesforceAccessToken()));
    }

}
