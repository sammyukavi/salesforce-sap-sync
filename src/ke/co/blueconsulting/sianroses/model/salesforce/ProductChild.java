package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps Products to Farm_Variety__c in Salesforce.
 * Syncing for products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTSCHILD")
public class ProductChild implements Serializable {
	
	private static final long serialVersionUID = 19325639692107L;
	@DatabaseField(columnName = "Push_to_SAP__c")
	boolean pushToSapC;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	private String flowerCodeC;
	@DatabaseField(columnName = "Pull_from_SAP__c")
	boolean pullFromSapC;
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private Long autoId;
	@DatabaseField(columnName = "Name")
	private String name;
	@DatabaseField(columnName = "Color__c")
	@SerializedName("Color__c")
	private String colorC;
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	private String classificationC;
	
	@DatabaseField(columnName = "Product_Family__c")
	@SerializedName("Family__c")
	private String productFamilyC;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm__c")
	private String farmC;
	@DatabaseField(columnName = "Is_Active__c")
	@SerializedName("Is_Active__c")
	private String isActiveC;
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	private String breederC;
	@DatabaseField(columnName = "Headsize__c")
	@SerializedName("Head_size__c")
	private String headSizeC;
	@DatabaseField(columnName = "Consumable_Stock__c")
	@SerializedName("Consumable_Stock__c")
	private String consumableStockC;
	@DatabaseField(columnName = "Length__c")
	@SerializedName("Length__c")
	private String lengthC;
	@DatabaseField(columnName = "SalesForceID")
	@SerializedName("Id")
	private String salesforceId;
	
	public Long getAutoId() {
		return autoId;
	}
	
	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	
	public String getFlowerCodeC() {
		return flowerCodeC;
	}
	
	public void setFlowerCodeC(String flowerCodeC) {
		this.flowerCodeC = flowerCodeC;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColorC() {
		return colorC;
	}
	
	public void setColorC(String colorC) {
		this.colorC = colorC;
	}
	
	public String getClassificationC() {
		return classificationC;
	}
	
	public void setClassificationC(String classificationC) {
		this.classificationC = classificationC;
	}
	
	public String getIsActiveC() {
		return isActiveC;
	}
	
	public void setIsActiveC(String isActiveC) {
		this.isActiveC = isActiveC;
	}
	
	public String getBreederC() {
		return breederC;
	}
	
	public void setBreederC(String breederC) {
		this.breederC = breederC;
	}
	
	public String getProductFamilyC() {
		return productFamilyC;
	}
	
	public void setProductFamilyC(String productFamilyC) {
		this.productFamilyC = productFamilyC;
	}
	
	public String getFarmC() {
		return farmC;
	}
	
	public void setFarmC(String farmC) {
		this.farmC = farmC;
	}
	
	public String getHeadSizeC() {
		return headSizeC;
	}
	
	public void setHeadSizeC(String headSizeC) {
		this.headSizeC = headSizeC;
	}
	
	public String getConsumableStockC() {
		return consumableStockC;
	}
	
	public void setConsumableStockC(String consumableStockC) {
		this.consumableStockC = consumableStockC;
	}
	
	public String getLengthC() {
		return lengthC;
	}
	
	public void setLengthC(String lengthC) {
		this.lengthC = lengthC;
	}
	
	public boolean isPushToSapC() {
		return pushToSapC;
	}
	
	public void setPushToSapC(boolean pushToSapC) {
		this.pushToSapC = pushToSapC;
	}
	
	public boolean isPullFromSapC() {
		return pullFromSapC;
	}
	
	public void setPullFromSapC(boolean pullFromSapC) {
		this.pullFromSapC = pullFromSapC;
	}
	
	public String getSalesforceId() {
		return salesforceId;
	}
	
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}
}
