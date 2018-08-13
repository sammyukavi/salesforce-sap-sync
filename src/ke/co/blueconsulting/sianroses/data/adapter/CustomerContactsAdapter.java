package ke.co.blueconsulting.sianroses.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.io.IOException;

public class CustomerContactsAdapter extends TypeAdapter<CustomerContacts> {
	
	@Override
	public void write(JsonWriter jsonWriter, CustomerContacts CustomerContacts) throws IOException {
		jsonWriter.beginObject();
		
		if (!StringUtils.isNullOrEmpty(CustomerContacts.getSalesforceId())) {
			jsonWriter.name("Id");
			jsonWriter.value(CustomerContacts.getSalesforceId());
		}
		
		jsonWriter.endObject();
	}
	
	@Override
	public CustomerContacts read(JsonReader jsonReader) {
		CustomerContacts CustomerContacts = new CustomerContacts();
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
					CustomerContacts.setSalesforceId(jsonReader.nextString());
				} else {
					jsonReader.skipValue();
				}
			}
			jsonReader.endObject();
		} catch (IOException e) {
			AppLogger.logError("An error occurred while trying to write a CustomerContacts object. " + e.getLocalizedMessage());
		}
		return CustomerContacts;
	}
	
}
