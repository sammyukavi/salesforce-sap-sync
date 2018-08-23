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
	public void write(JsonWriter jsonWriter, CustomerContact customerContact) throws IOException {
		jsonWriter.beginObject();
		
		if (!StringUtils.isNullOrEmpty(customerContact.getAccountId())) {
			jsonWriter.name("AccountId");
			jsonWriter.value(customerContact.getAccountId());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getAccountNumber())) {
			jsonWriter.name("Account_Number__c");
			jsonWriter.value(customerContact.getAccountNumber());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getBirthDate())) {
			jsonWriter.name("Birthdate");
			jsonWriter.value(customerContact.getBirthDate());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getContactId())) {
			jsonWriter.name("Id");
			jsonWriter.value(customerContact.getContactId());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getDepartment())) {
			jsonWriter.name("Department");
			jsonWriter.value(customerContact.getDepartment());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getEmail())) {
			jsonWriter.name("Email");
			jsonWriter.value(customerContact.getEmail());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getFax())) {
			jsonWriter.name("Fax");
			jsonWriter.value(customerContact.getFax());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getFirstName())) {
			jsonWriter.name("FirstName");
			jsonWriter.value(customerContact.getFirstName());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getLastName())) {
			jsonWriter.name("LastName");
			jsonWriter.value(customerContact.getLastName());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMailingCity())) {
			jsonWriter.name("MailingCity");
			jsonWriter.value(customerContact.getMailingCity());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMailingCountry())) {
			jsonWriter.name("MailingCountry");
			jsonWriter.value(customerContact.getMailingCountry());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMailingPostalCode())) {
			jsonWriter.name("MailingPostalCode");
			jsonWriter.value(customerContact.getMailingPostalCode());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMailingState())) {
			jsonWriter.name("MailingState");
			jsonWriter.value(customerContact.getMailingState());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMailingStreet())) {
			jsonWriter.name("MailingStreet");
			jsonWriter.value(customerContact.getMailingStreet());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getMobilePhone())) {
			jsonWriter.name("MobilePhone");
			jsonWriter.value(customerContact.getMobilePhone());
		}
		
		if (!StringUtils.isNullOrEmpty(customerContact.getPhone())) {
			jsonWriter.name("Phone");
			jsonWriter.value(customerContact.getPhone());
		}
		
		jsonWriter.name("Pull_from_SAP__c");
		jsonWriter.value(customerContact.isPullFromSAP());
		
		jsonWriter.name("Push_to_SAP__c");
		jsonWriter.value(customerContact.isPushToSAP());
		
		if (StringUtils.isNullOrEmpty(customerContact.getTitle())) {
			jsonWriter.name("Title");
			jsonWriter.value(customerContact.getTitle());
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
				} else if ("CONTACTID".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setContactId(jsonReader.nextString());
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
					CustomerContact.setPushToSAP(jsonReader.nextBoolean());
				} else if ("Pull_from_SAP__c".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setPullFromSAP(jsonReader.nextBoolean());
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
