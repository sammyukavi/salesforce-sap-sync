package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

@DatabaseTable(tableName = "STOCKMANAGEMENT")
public class Stock extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 6437225639692107L;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCode;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Warehouse__c")
	@SerializedName("Warehouse__c")
	@Expose
	private String warehouse;
	
	@DatabaseField(columnName = "Quantity__c")
	@SerializedName("Quantity__c")
	@Expose
	private String quantity;
	
	@DatabaseField(columnName = "Farm_Code__c")
	@SerializedName("Farm_Code__c")
	@Expose
	private String farmCode;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm__c")
	@Expose
	private String farm;
	
	public String getFlowerCode() {
		return flowerCode;
	}
	
	public void setFlowerCode(String flowerCode) {
		this.flowerCode = flowerCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWarehouse() {
		return warehouse;
	}
	
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getFarmCode() {
		return farmCode;
	}
	
	public void setFarmCode(String farmCode) {
		this.farmCode = farmCode;
	}
	
	public String getFarm() {
		return farm;
	}
	
	public void setFarm(String farm) {
		this.farm = farm;
	}
}
