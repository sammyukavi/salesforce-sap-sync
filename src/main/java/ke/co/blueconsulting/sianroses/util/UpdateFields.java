package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;

import java.util.ArrayList;

public class UpdateFields {
	
	public static ArrayList<CustomerContact> updateContactSyncFields(ArrayList<CustomerContact> customerContacts, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<CustomerContact> updatedCustomers = new ArrayList<>();
		for (CustomerContact customerContact : customerContacts) {
			customerContact.setPushToSAP(pushToSAP);
			customerContact.setPullFromSAP(pullFromSAP);
			updatedCustomers.add(customerContact);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<? extends BaseSalesforceModel> updateSyncFields(ArrayList<? extends BaseSalesforceModel> models, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<BaseSalesforceModel> updatedModels = new ArrayList<>();
		for (BaseSalesforceModel model : models) {
			model.setPushToSap(pushToSAP);
			model.setPullFromSap(pullFromSAP);
			updatedModels.add(model);
		}
		return updatedModels;
	}
}
