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
				if ("Id".equals(fieldname)) {
					jsonReader.peek();
					CustomerContact.setSalesforceId(jsonReader.nextString());
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
