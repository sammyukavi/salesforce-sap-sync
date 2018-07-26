package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SalesforceAuthCredentials implements Serializable {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	@SerializedName("access_token")
	private String accessToken;
	
	@Expose
	@SerializedName("id")
	private String id;
	
	@SerializedName("instance_url")
	private String instanceUrl;
	
	@SerializedName("issued_at")
	private String issuedAt;
	
	@Expose
	private String signature;
	
	@SerializedName("token_type")
	private String tokenType;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public String getId() {
		return id;
	}
	
	public String getInstanceUrl() {
		return instanceUrl;
	}
	
	public String getIssuedAt() {
		return issuedAt;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public String getTokenType() {
		return tokenType;
	}
	
	public static class Builder {
		
		private String accessToken;
		private String id;
		private String instanceUrl;
		private String issuedAt;
		private String signature;
		private String tokenType;
		
		public SalesforceAuthCredentials.Builder withAccessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}
		
		public SalesforceAuthCredentials.Builder withId(String id) {
			this.id = id;
			return this;
		}
		
		public SalesforceAuthCredentials.Builder withInstanceUrl(String instanceUrl) {
			this.instanceUrl = instanceUrl;
			return this;
		}
		
		public SalesforceAuthCredentials.Builder withIssuedAt(String issuedAt) {
			this.issuedAt = issuedAt;
			return this;
		}
		
		public SalesforceAuthCredentials.Builder withSignature(String signature) {
			this.signature = signature;
			return this;
		}
		
		public SalesforceAuthCredentials.Builder withTokenType(String tokenType) {
			this.tokenType = tokenType;
			return this;
		}
		
		public String getAccessToken() {
			return accessToken;
		}
		
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		
		public String getId() {
			return id;
		}
		
		public void setId(String id) {
			this.id = id;
		}
		
		public String getInstanceUrl() {
			return instanceUrl;
		}
		
		public void setInstanceUrl(String instanceUrl) {
			this.instanceUrl = instanceUrl;
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
		
		public String getTokenType() {
			return tokenType;
		}
		
		public void setTokenType(String tokenType) {
			this.tokenType = tokenType;
		}
		
		public SalesforceAuthCredentials build() {
			SalesforceAuthCredentials salesforceAuthCredentials = new SalesforceAuthCredentials();
			salesforceAuthCredentials.accessToken = accessToken;
			salesforceAuthCredentials.id = id;
			salesforceAuthCredentials.instanceUrl = instanceUrl;
			salesforceAuthCredentials.issuedAt = issuedAt;
			salesforceAuthCredentials.signature = signature;
			salesforceAuthCredentials.tokenType = tokenType;
			return salesforceAuthCredentials;
		}
		
	}
	
}
