package ke.co.blueconsulting.sianroses.data.impl;

import ke.co.blueconsulting.sianroses.data.BaseDataService;
import ke.co.blueconsulting.sianroses.data.rest.AuthRestService;
import ke.co.blueconsulting.sianroses.model.app.SalesforceAuthCredentials;

import static ke.co.blueconsulting.sianroses.util.Constants.RequestKeys.GRANT_TYPE_PASSWORD;

/**
 * A service that is used to authenticate the app with the Salesforce Client
 */
public class AuthDataService extends BaseDataService<SalesforceAuthCredentials, AuthRestService> {
	
	@Override
	protected Class<AuthRestService> getRestServiceClass() {
		return AuthRestService.class;
	}
	
	public void authenticate(String salesforceClientId, String salesforceClientSecret,
	                         String salesforceUsername, String salesforcePassword, String salesforceSecurityToken,
	                         GetCallback<SalesforceAuthCredentials> callback) {
		executeSingleTask(callback, restService.authenticate(salesforceClientId, salesforceClientSecret,
				GRANT_TYPE_PASSWORD, salesforceUsername, salesforcePassword + salesforceSecurityToken));
	}
}
