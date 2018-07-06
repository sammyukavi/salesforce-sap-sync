package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.salesforce.Error;
import retrofit2.Response;

import java.io.IOException;
import java.io.Serializable;

public class ErrorUtil implements Serializable {
	
	public static Error parseError(Response<?> response) throws IOException {
		Error error = new Error();
		return error;
	}
}
