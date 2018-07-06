package ke.co.blueconsulting.sianroses.model.app;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This model is used for storing the user credentials used to login to MSSQL Server.
 */
@DatabaseTable(tableName = "auth_credentials")
public class AppAuthCredentials {
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@DatabaseField(columnName = "server_address")
	private String serverAddress;
	
	@DatabaseField(columnName = "server_port")
	private int serverPort;
	
	@DatabaseField(columnName = "db_name")
	private String databaseName;
	
	@DatabaseField(columnName = "db_username")
	private String databaseUsername;
	
	@DatabaseField(columnName = "db_password")
	private String databasePassword;
	
	@DatabaseField(columnName = "sync_period")
	private int syncPeriod;
	
	@DatabaseField(columnName = "sync_period_unit")
	private String syncPeriodUnit;
	
	@DatabaseField(columnName = "salesforce_client_id")
	private String salesforceClientId;
	
	@DatabaseField(columnName = "salesforce_client_secret")
	private String salesforceClientSecret;
	
	@DatabaseField(columnName = "salesforce_username")
	private String salesforceUsername;
	
	@DatabaseField(columnName = "salesforce_password")
	private String salesforcePassword;
	
	@DatabaseField(columnName = "salesforce_security_token")
	private String salesforceSecurityToken;
	
	@DatabaseField(columnName = "salesforce_access_token")
	private String salesforceAccessToken;
	
	@DatabaseField(columnName = "salesforce_id")
	private String salesforceId;
	
	@DatabaseField(columnName = "instance_url")
	private String instanceUrl;
	
	@DatabaseField(columnName = "issued_at")
	private String issuedAt;
	
	@DatabaseField(columnName = "signature")
	private String signature;
	
	@DatabaseField(columnName = "token_type")
	private String tokenType;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getServerAddress() {
		return serverAddress;
	}
	
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
	public String getDatabaseName() {
		return databaseName;
	}
	
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	public String getDatabaseUsername() {
		return databaseUsername;
	}
	
	public void setDatabaseUsername(String databaseUsername) {
		this.databaseUsername = databaseUsername;
	}
	
	public String getDatabasePassword() {
		return databasePassword;
	}
	
	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}
	
	public int getSyncPeriod() {
		return syncPeriod;
	}
	
	public void setSyncPeriod(int syncPeriod) {
		this.syncPeriod = syncPeriod;
	}
	
	public String getSyncPeriodUnit() {
		return syncPeriodUnit;
	}
	
	public void setSyncPeriodUnit(String syncPeriodUnit) {
		this.syncPeriodUnit = syncPeriodUnit;
	}
	
	public String getSalesforceClientId() {
		return salesforceClientId;
	}
	
	public void setSalesforceClientId(String salesforceClientId) {
		this.salesforceClientId = salesforceClientId;
	}
	
	public String getSalesforceClientSecret() {
		return salesforceClientSecret;
	}
	
	public void setSalesforceClientSecret(String salesforceClientSecret) {
		this.salesforceClientSecret = salesforceClientSecret;
	}
	
	public String getSalesforceUsername() {
		return salesforceUsername;
	}
	
	public void setSalesforceUsername(String salesforceUsername) {
		this.salesforceUsername = salesforceUsername;
	}
	
	public String getSalesforcePassword() {
		return salesforcePassword;
	}
	
	public void setSalesforcePassword(String salesforcePassword) {
		this.salesforcePassword = salesforcePassword;
	}
	
	public String getSalesforceSecurityToken() {
		return salesforceSecurityToken;
	}
	
	public void setSalesforceSecurityToken(String salesforceSecurityToken) {
		this.salesforceSecurityToken = salesforceSecurityToken;
	}
	
	public String getSalesforceAccessToken() {
		return salesforceAccessToken;
	}
	
	public void setSalesforceAccessToken(String salesforceAccessToken) {
		this.salesforceAccessToken = salesforceAccessToken;
	}
	
	public String getSalesforceId() {
		return salesforceId;
	}
	
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
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
}
