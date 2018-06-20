package ke.co.blueconsulting.sianroses.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ServerResponse implements Serializable {
  
  @SerializedName("server_time")
  private String serverTime;
  
  @SerializedName("api_version")
  private Long apiVersion;
  
  @SerializedName("status")
  private Long status;
  
  public String getServerTime() {
    return serverTime;
  }
  
  public void setServerTime(String serverTime) {
    this.serverTime = serverTime;
  }
  
  public Long getApiVersion() {
    return apiVersion;
  }
  
  public void setApiVersion(Long apiVersion) {
    this.apiVersion = apiVersion;
  }
  
  public Long getStatus() {
    return status;
  }
  
  public void setStatus(Long status) {
    this.status = status;
  }
	
	/*@Override
	public String toString() {
		return
				"ServerResponse{" +
						"server_time = '" + serverTime + '\'' +
						",data = '" + data + '\'' +
						",api_version = '" + apiVersion + '\'' +
						",status = '" + status + '\'' +
						"}";
	}*/
}