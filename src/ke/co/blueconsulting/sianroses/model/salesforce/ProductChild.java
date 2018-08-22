package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.data.adapter.EmptyStringTypeAdapter;

import java.io.Serializable;

/**
 * This model maps Products to Farm_Variety__c in Salesforce.
 * Syncing for products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTSCHILD")
public class ProductChild implements Serializable {
	
	private static final long serialVersionUID = 19325639692107L;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	private boolean pushToSapC;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCodeC;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	private boolean pullFromSapC;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("autoId__c")
	@Expose
	private Long autoId;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Color__c")
	@SerializedName("Color__c")
	@Expose
	private String colorC;
	
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	@Expose
	private String classificationC;
	
	@DatabaseField(columnName = "Product_Family__c")
	@SerializedName("Family__c")
	@Expose
	private String productFamilyC;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm__c")
	@Expose
	private String farmC;
	
	@DatabaseField(columnName = "Is_Active__c")
	@SerializedName("Is_Active__c")
	@Expose
	private boolean isActiveC;
	
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	@Expose
	private String breederC;
	
	@DatabaseField(columnName = "Headsize__c")
	@SerializedName("Head_size__c")
	@Expose
	private String headSizeC;
	
	@DatabaseField(columnName = "Consumable_Stock__c")
	@SerializedName("Consumable_Stock__c")
	@Expose
	private String consumableStockC;
	
	@DatabaseField(columnName = "Length__c")
	@SerializedName("Length__c")
	@Expose
	private String lengthC;
	
	@DatabaseField(columnName = "SalesForceID")
	@SerializedName("Id")
	@Expose
	@JsonAdapter(EmptyStringTypeAdapter.class)
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
	
	public boolean getIsActiveC() {
		return isActiveC;
	}
	
	public void setIsActiveC(boolean isActiveC) {
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
