package ke.co.blueconsulting.sianroses.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.io.IOException;

public class CustomerContactsAdapter extends TypeAdapter<CustomerContact> {
	
	@Override
	public void write(JsonWriter jsonWriter, CustomerContact CustomerContact) throws IOException {
		jsonWriter.beginObject();
		
		if (!StringUtils.isNullOrEmpty(CustomerContact.getSalesforceId())) {
			jsonWriter.name("Id");
			jsonWriter.value(CustomerContact.getSalesforceId());
		}
		
		jsonWriter.endObject();
	}
	
	@Override
	public CustomerContact read(JsonReader jsonReader) {
		CustomerContact CustomerContact = new CustomerContact();
		try {
			jsonReader.beginObject();
			String fieldname = null;
			while (jsonReader.hasNext()) {
				JsonToken token = jsonReader.peek();
				if (token.equals(JsonToken.NAME)) {
					fieldname = jsonReader.nextName();
				}
				if ("AccountId".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setAccountId(jsonReader.nextString());
				} else if ("Account_Number__c".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setAccountNumber(jsonReader.nextString());
				} else if ("Birthdate".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setBirthDate(jsonReader.nextString());
				} /*else if ("CONTACTID".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setContactId(jsonReader.nextString());
				} */ else if ("Id".equals(fieldname)) {
					jsonReader.peek();
					String id = jsonReader.nextString();
					CustomerContact.setSalesforceId(id);
					CustomerContact.setContactId(id);
				} else if ("Department".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setDepartment(jsonReader.nextString());
				} else if ("Email".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setEmail(jsonReader.nextString());
				} else if ("Fax".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setFax(jsonReader.nextString());
				} else if ("FirstName".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setFirstName(jsonReader.nextString());
				} else if ("LastName".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setLastName(jsonReader.nextString());
				} else if ("MailingCity".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMailingCity(jsonReader.nextString());
				} else if ("MailingCountry".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMailingCountry(jsonReader.nextString());
				} else if ("MailingPostalCode".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMailingPostalCode(jsonReader.nextString());
				} else if ("MailingState".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMailingState(jsonReader.nextString());
				} else if ("MailingStreet".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMailingStreet(jsonReader.nextString());
				} else if ("MobilePhone".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setMobilePhone(jsonReader.nextString());
				} else if ("Phone".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setPhone(jsonReader.nextString());
				} else if ("Push_to_SAP__c".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setPushToSap(jsonReader.nextBoolean());
				} else if ("Pull_from_SAP__c".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setPullFromSap(jsonReader.nextBoolean());
				} else if ("Title".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setTitle(jsonReader.nextString());
				} else {
					jsonReader.skipValue();
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			AppLogger.logError("An error occurred while trying to write a contact object. " + e.getMessage());
		}
		return CustomerContact;
	}
	
}
