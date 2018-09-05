package ke.co.blueconsulting.sianroses.data.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class EmptyStringTypeAdapter extends TypeAdapter<String> {
	
	private EmptyStringTypeAdapter() {
	
	}
	
	@Override
	@SuppressWarnings("resource")
	public void write(final JsonWriter jsonWriter, final String s)
			throws IOException {
		if (s == null || s.isEmpty()) {
			jsonWriter.nullValue();
		} else {
			jsonWriter.value(s);
		}
	}
	
	@Override
	public String read(JsonReader jsonReader) throws IOException {
		final JsonToken token = jsonReader.peek();
		switch (token) {
			case NULL:
				return "";
			case STRING:
				return jsonReader.nextString();
			default:
				throw new IllegalStateException("Unexpected token: " + token);
		}
	}
	
	
}
