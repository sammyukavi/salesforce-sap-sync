package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.model.salesforce.Warehouse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServerResponse<S> implements Serializable {
  
  @SerializedName("data")
  @Expose
  private Map<String, List<S>> data = new HashMap<>();
  
  @SerializedName("access_token")
  @Expose
  private String accessToken;
  
  @SerializedName("instance_url")
  @Expose
  private String instanceUrl;
  
  @SerializedName("id")
  @Expose
  private String id;
  
  @SerializedName("token_type")
  @Expose
  private String tokenType;
  
  @SerializedName("issued_at")
  @Expose
  private String issuedAt;
  
  @SerializedName("signature")
  @Expose
  private String signature;
  
  @SerializedName("error")
  @Expose
  private String error;
  
  @SerializedName("error_description")
  @Expose
  private String errorDescription;
  
  @SerializedName("pricelist")
  private List<PriceList> pricelist;
  
  @SerializedName("warehouses")
  private List<Warehouse> warehouses;
  
  @SerializedName("customers")
  private List<Customer> customers;
  
  @SerializedName("products")
  private List<Product> products;
  
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
  
  public String getAccessToken() {
    return accessToken;
  }
  
  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
  
  public String getInstanceUrl() {
    return instanceUrl;
  }
  
  public void setInstanceUrl(String instanceUrl) {
    this.instanceUrl = instanceUrl;
  }
  
  public String getId() {
    return id;
  }
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getTokenType() {
    return tokenType;
  }
  
  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }
  
  public String getIssuedAt() {
    return issuedAt;
  }
  
  public void setIssuedAt(String issuedAt) {
    this.issuedAt = issuedAt;
  }
  
  public String getSignature() {
    return signature;
  }
  
  public void setSignature(String signature) {
    this.signature = signature;
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
  
  public ServerResponse withError(String error) {
    this.error = error;
    return this;
  }
  
  public ServerResponse withErrorDescription(String errorDescription) {
    this.errorDescription = errorDescription;
    return this;
  }
  
}