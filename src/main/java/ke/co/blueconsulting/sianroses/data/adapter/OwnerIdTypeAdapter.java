package ke.co.blueconsulting.sianroses.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import ke.co.blueconsulting.sianroses.model.salesforce.User;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.io.IOException;

public class OwnerIdTypeAdapter extends TypeAdapter<User> {
	
	private OwnerIdTypeAdapter() {
	}
	
	@Override
	public void write(JsonWriter jsonWriter, User user) throws IOException {
		String userCode = user.getUserCode();
		if (StringUtils.isNullOrEmpty(userCode) || StringUtils.isBlank(userCode)) {
			jsonWriter.nullValue();
		}
		jsonWriter.value(userCode);
	}
	
	@Override
	public User read(JsonReader jsonReader) throws IOException {
		final JsonToken token = jsonReader.peek();
		switch (token) {
			case NULL:
				return new User();
			case STRING:
				User user = new User();
				user.setUserCode(jsonReader.nextString());
				return user;
			default:
				throw new IllegalStateException("Unexpected token: " + token);
		}
	}
	
	
}
