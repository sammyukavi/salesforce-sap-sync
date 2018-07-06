package ke.co.blueconsulting.sianroses.util;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ke.co.blueconsulting.sianroses.model.app.Error;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_UNSUPPORTED_ELEMENT;

public class ErrorsDeserializer implements JsonDeserializer<ErrorsDeserializer.Errors> {
	
	public static class Errors {
		
		private List<Error> errors;
		
		Errors(Error... errors) {
			this.errors = Arrays.asList(errors);
		}
		
		List<Error> getErrors() {
			return errors;
		}
		
		public void setErrors(List<Error> errors) {
			this.errors = errors;
		}
	}
	
	
	@Override
	public Errors deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
			JsonParseException {
		if (json.isJsonArray()) {
			return new Errors((Error[]) context.deserialize(json.getAsJsonArray(), Error[].class));
		} else if (json.isJsonObject()) {
			return new Errors((Error) context.deserialize(json.getAsJsonObject(), Error.class));
		} else {
			throw new JsonParseException(StringUtils.getString(MESSAGE_UNSUPPORTED_ELEMENT));
		}
	}
	
	
}