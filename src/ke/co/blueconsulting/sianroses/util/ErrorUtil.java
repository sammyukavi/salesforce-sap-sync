package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.model.app.Error;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

import java.lang.annotation.Annotation;

public class ErrorUtil {
	
	public static String parseError(Response<?> response) {
		StringBuilder message = new StringBuilder();
		Converter<ResponseBody, ErrorsDeserializer.Errors> converter = RestServiceBuilder.retrofit()
				.responseBodyConverter(ErrorsDeserializer.Errors.class, new Annotation[0]);
		try {
			ErrorsDeserializer.Errors serverResponse = converter.convert(response.errorBody());
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
			e.printStackTrace();
		}
		
		return message.toString();
	}
}