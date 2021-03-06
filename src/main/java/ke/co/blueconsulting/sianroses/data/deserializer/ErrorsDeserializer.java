package ke.co.blueconsulting.sianroses.data.deserializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ke.co.blueconsulting.sianroses.model.app.Error;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.UIStringKeys.MESSAGE_UNSUPPORTED_ELEMENT;

public class ErrorsDeserializer implements JsonDeserializer<ErrorsDeserializer.Parser> {
	
	@Override
	public Parser deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
			JsonParseException {
		if (json.isJsonArray()) {
			return new Parser((Error[]) context.deserialize(json.getAsJsonArray(), Error[].class));
		} else if (json.isJsonObject()) {
			return new Parser((Error) context.deserialize(json.getAsJsonObject(), Error.class));
		} else {
			throw new JsonParseException(StringUtils.getString(MESSAGE_UNSUPPORTED_ELEMENT));
		}
	}
	
	
	public static class Parser {
		
		private List<Error> errors;
		
		Parser(Error... errors) {
			this.errors = Arrays.asList(errors);
		}
		
		public List<Error> getErrors() {
			return errors;
		}
		
	}
	
}
