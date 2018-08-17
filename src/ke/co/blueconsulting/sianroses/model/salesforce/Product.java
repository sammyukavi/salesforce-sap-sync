package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * This model maps Products to Products2 in Salesforce.
 * Syncing for Products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTS")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 19365904690402L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("ERP_ID__c")
	@Expose
	private Long autoId;
	
	@DatabaseField(columnName = "productcode")
	@SerializedName("ProductCode")
	@Expose
	private String productCode;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "IsActive")
	@SerializedName("IsActive")
	@Expose
	private boolean isActive;
	
	@DatabaseField(columnName = "Color__c")
	@SerializedName("Color__c")
	@Expose
	private String colorC;
	
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	@Expose
	private String classificationC;
	
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	@Expose
	private String breederC;
	
	/*@DatabaseField(columnName = "Family")
	@SerializedName("Family")
	@Expose
	private String family;*/
	
	@DatabaseField(columnName = "Product_Type__c")
	@SerializedName("Product_Type__c")
	@Expose
	private String productTypeC;
	
	@DatabaseField(columnName = "Parent_Product__c")
	@SerializedName("Parent_Product__c")
	@Expose
	private String parentProductC;
	
	@DatabaseField(columnName = "Parent_Product_Code__c")
	@SerializedName("Parent_Product_Code__c")
	@Expose
	private String parentProductCodeC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	private String salesforceId;
	
	@DatabaseField(columnName = "product_Category")
	@SerializedName("Family")
	@Expose
	private String productCategory;
	
	public Long getAutoId() {
		return autoId;
	}
	
	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	
	public String getProductCode() {
		return productCode;
	}
	
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean active) {
		isActive = active;
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
	
	public String getBreederC() {
		return breederC;
	}
	
	public void setBreederC(String breederC) {
		this.breederC = breederC;
	}
	
	public String getProductTypeC() {
		return productTypeC;
	}
	
	public void setProductTypeC(String productTypeC) {
		this.productTypeC = productTypeC;
	}
	
	public String getParentProductC() {
		return parentProductC;
	}
	
	public void setParentProductC(String parentProductC) {
		this.parentProductC = parentProductC;
	}
	
	public String getParentProductCodeC() {
		return parentProductCodeC;
	}
	
	public void setParentProductCodeC(String parentProductCodeC) {
		this.parentProductCodeC = parentProductCodeC;
	}
	
	public boolean isPushToSAPC() {
		return pushToSAPC;
	}
	
	public void setPushToSAPC(boolean pushToSAPC) {
		this.pushToSAPC = pushToSAPC;
	}
	
	public boolean isPullFromSAPC() {
		return pullFromSAPC;
	}
	
	public void setPullFromSAPC(boolean pullFromSAPC) {
		this.pullFromSAPC = pullFromSAPC;
	}
	
	public String getSalesforceId() {
		return salesforceId;
	}
	
	public void setSalesforceId(String salesforceId) {
		this.salesforceId = salesforceId;
	}
	
	public String getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
}
