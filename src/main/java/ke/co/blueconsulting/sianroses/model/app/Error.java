package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Error implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName("error")
	@Expose
	private String error;
	
	@SerializedName("error_description")
	@Expose
	private String errorDescription;
	
	@SerializedName("message")
	@Expose
	private String message;
	
	@SerializedName("errorCode")
	@Expose
	private String errorCode;
	
	public String getErrorDescription() {
		return errorDescription;
	}
	
	public String getMessage() {
		return message;
	}
	
}
