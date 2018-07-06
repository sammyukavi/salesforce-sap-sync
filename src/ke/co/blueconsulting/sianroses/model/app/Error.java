
package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.SerializedName;

public class Error {
	
	@SerializedName("error")
	private String error;
	
	@SerializedName("error_description")
	private String errorDescription;
	
	@SerializedName("message")
	private String message;
	
	@SerializedName("errorCode")
	private String errorCode;
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public static class Builder {
		public Error build() {
			Error error = new Error();
			return error;
		}
		
	}
	
}
