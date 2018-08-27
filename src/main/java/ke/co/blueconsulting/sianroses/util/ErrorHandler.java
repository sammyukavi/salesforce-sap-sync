package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.deserializer.ErrorsDeserializer;
import ke.co.blueconsulting.sianroses.model.app.Error;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import java.lang.annotation.Annotation;

public class ErrorHandler {
	
	public static String parseError(Response<?> response) {
		StringBuilder message = new StringBuilder();
		Converter<ResponseBody, ErrorsDeserializer.Parser> converter = RestServiceBuilder.retrofit()
				.responseBodyConverter(ErrorsDeserializer.Parser.class, new Annotation[0]);
		try {
			ErrorsDeserializer.Parser serverResponse = converter.convert(response.errorBody());
			for (Error error : serverResponse.getErrors()) {
				
				if (!StringUtils.isBlank(error.getErrorDescription()) && !StringUtils.isNullOrEmpty(error
						.getErrorDescription())) {
					message.append(error.getErrorDescription());
				}
				
				if (!StringUtils.isBlank(error.getMessage()) && !StringUtils.isNullOrEmpty(error.getMessage())) {
					message.append(error.getMessage());
				}
			}
		} catch (Exception e) {
			AppLogger.logError("An error occurred when trying to desirialize the returned server error. " + e.getMessage());
		}
		return message.toString();
	}
}
