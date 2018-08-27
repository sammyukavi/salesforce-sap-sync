package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;
//One way push to salesforce

/**
 * This model maps SAP Warehouse to Salesforce Warehouse.
 * Syncing for warehouses is a one way push from SAP to Salesforce.
 */
@DatabaseTable(tableName = "WAREHOUSE")
public class Warehouse extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm_SAP__c")
	@Expose
	private String farm;
	
	@DatabaseField(columnName = "Farm_Code__c")
	@SerializedName("Farm_Code__c")
	@Expose
	private String farmCode;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("SAP_ID__c")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Warehouse_Code__c")
	@SerializedName("Warehouse_Code__c")
	@Expose
	private String warehouseCode;
	
	public String getFarm() {
		return farm;
	}
	
	public void setFarm(String farm) {
		this.farm = farm;
	}
	
	public String getFarmCode() {
		return farmCode;
	}
	
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWarehouseCode() {
		return warehouseCode;
	}
	
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
}
