package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.data.adapter.EmptyStringTypeAdapter;

import java.io.Serializable;
//One way push to salesforce

/**
 * This model maps SAP Warehouse to Salesforce Warehouse.
 * Syncing for warehouses is a one way push from SAP to Salesforce.
 */
@DatabaseTable(tableName = "WAREHOUSE")
public class Warehouse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("Auto_Id__c")
	@Expose
	private int autoId;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	@JsonAdapter(EmptyStringTypeAdapter.class)
	private String SalesForceId;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm_SAP__c")
	@Expose
	private String farmC;
	
	@DatabaseField(columnName = "Farm_Code__c")
	@SerializedName("Farm_Code__c")
	@Expose
	private String farmCodeC;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC;
	
	@DatabaseField(columnName = "Warehouse_Code__c")
	@SerializedName("Warehouse_Code__c")
	@Expose
	private String warehouseCodeC;
	
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getSalesForceId() {
		return SalesForceId;
	}
	
	public void setSalesForceId(String salesForceId) {
		SalesForceId = salesForceId;
	}
	
	public String getFarmC() {
		return farmC;
	}
	
	public void setFarmC(String farmC) {
		this.farmC = farmC;
	}
	
	public String getFarmCodeC() {
		return farmCodeC;
	}
	
	public void setFarmCodeC(String farmCodeC) {
		this.farmCodeC = farmCodeC;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isPullFromSAPC() {
		return pullFromSAPC;
	}
	
	public void setPullFromSAPC(boolean pullFromSAPC) {
		this.pullFromSAPC = pullFromSAPC;
	}
	
	public boolean isPushToSAPC() {
		return pushToSAPC;
	}
	
	public void setPushToSAPC(boolean pushToSAPC) {
		this.pushToSAPC = pushToSAPC;
	}
	
	public String getWarehouseCodeC() {
		return warehouseCodeC;
	}
	
	public void setWarehouseCodeC(String warehouseCodeC) {
		this.warehouseCodeC = warehouseCodeC;
	}
}
