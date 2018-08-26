package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

/**
 * This model maps Products to Farm_Variety__c in Salesforce.
 * Syncing for products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTSCHILD")
public class ProductChild extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 19325639692107L;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCode;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Color__c")
	@SerializedName("Color__c")
	@Expose
	private String color;
	
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	@Expose
	private String classification;
	
	@DatabaseField(columnName = "Product_Family__c")
	@SerializedName("Family__c")
	@Expose
	private String productFamily;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm__c")
	@Expose
	private String farm;
	
	@DatabaseField(columnName = "Is_Active__c")
	@SerializedName("Is_Active__c")
	@Expose
	private boolean isActive;
	
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	@Expose
	private String breeder;
	
	@DatabaseField(columnName = "Headsize__c")
	@SerializedName("Head_size__c")
	@Expose
	private String headSize;
	
	@DatabaseField(columnName = "Consumable_Stock__c")
	@SerializedName("Consumable_Stock__c")
	@Expose
	private String consumableStock;
	
	@DatabaseField(columnName = "Length__c")
	@SerializedName("Length__c")
	@Expose
	private String length;
	
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
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public String getClassification() {
		return classification;
	}
	
	public void setClassification(String classification) {
		this.classification = classification;
	}
	
	public boolean getIsActiveC() {
		return isActive;
	}
	
	public void setIsActiveC(boolean isActiveC) {
		this.isActive = isActiveC;
	}
	
	public String getBreeder() {
		return breeder;
	}
	
	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}
	
	public String getProductFamily() {
		return productFamily;
	}
	
	public void setProductFamily(String productFamily) {
		this.productFamily = productFamily;
	}
	
	public String getFarm() {
		return farm;
	}
	
	public void setFarm(String farm) {
		this.farm = farm;
	}
	
	public String getHeadSize() {
		return headSize;
	}
	
	public void setHeadSize(String headSize) {
		this.headSize = headSize;
	}
	
	public String getConsumableStock() {
		return consumableStock;
	}
	
	public void setConsumableStock(String consumableStock) {
		this.consumableStock = consumableStock;
	}
	
	public String getLength() {
		return length;
	}
	
	public void setLength(String length) {
		this.length = length;
	}
	
}
