package ke.co.blueconsulting.sianroses.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServerResponse<S> implements Serializable {
  
  @SerializedName("data")
  @Expose
  private Map<String, List<S>> data = new HashMap<>();
  
  public void addData(String key, List<S> value) {
    data.put(key, value);
  }
  
  public Map<String, List<S>> getData() {
    return data;
  }
  
  public void setData(Map<String, List<S>> data) {
    this.data.clear();
    this.data.putAll(data);
  }
  
  @Override
  public String toString() {
    return
        "ServerResponse{" +
            //"server_time = '" + serverTime + '\'' +
            ",data = '" + data + '\'' +
            // ",api_version = '" + apiVersion + '\'' +
            "}";
  }
}