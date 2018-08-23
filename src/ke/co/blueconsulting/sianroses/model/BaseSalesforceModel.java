package ke.co.blueconsulting.sianroses.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import ke.co.blueconsulting.sianroses.data.adapter.EmptyStringTypeAdapter;

import java.io.Serializable;

public class BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 189527097474049L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("Auto_Id__c")
	@Expose
		private int autoId;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSap;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSap;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	@JsonAdapter(EmptyStringTypeAdapter.class)
	private String salesforceId;
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public boolean isPullFromSap() {
		return pullFromSap;
	}
	
	public void setPullFromSap(boolean pullFromSap) {
		this.pullFromSap = pullFromSap;
	}
	
	public boolean isPushToSap() {
		return pushToSap;
	}
	
	public void setPushToSap(boolean pushToSap) {
		this.pushToSap = pushToSap;
	}
	
	public String getSalesforceId() {
		return salesforceId;
	}
	
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}
}
