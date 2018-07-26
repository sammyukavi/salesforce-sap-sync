package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Error implements Serializable {

	private static final long serialVersionUID = 1L;

	@SerializedName("error")
    private String error;

    @SerializedName("error_description")
    private String errorDescription;

    @SerializedName("message")
    private String message;

    @SerializedName("errorCode")
    private String errorCode;

    public String getErrorDescription() {
        return errorDescription;
    }

    public String getMessage() {
        return message;
    }

}
