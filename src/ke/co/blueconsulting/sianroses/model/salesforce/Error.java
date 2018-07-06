
package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {
	
	@SerializedName("error")
	@Expose
	private String error = "";
	
	@SerializedName("error_description")
	@Expose
	private String errorDescription = "";
	
	
	public static class Builder {
		public Error build() {
			Error error = new Error();
			return error;
		}
	}
	
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
}
