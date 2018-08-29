package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

/**
 * This model maps Products to Products2 in Salesforce.
 * Syncing for Products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTS")
public class Product extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 19365904690402L;
	
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
	private String color;
	
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	@Expose
	private String classification;
	
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	@Expose
	private String breeder;
	
	@DatabaseField(columnName = "Family")
	private String family;
	
	@DatabaseField(columnName = "Product_Type__c")
	@SerializedName("Product_Type__c")
	@Expose
	private String productType;
	
	@DatabaseField(columnName = "Parent_Product__c")
	@SerializedName("Parent_Product__c")
	@Expose
	private String parentProduct;
	
	@DatabaseField(columnName = "Parent_Product_Code__c")
	@SerializedName("Parent_Product_Code__c")
	@Expose
	private String parentProductCode;
	
	@DatabaseField(columnName = "product_Category")
	@SerializedName("Family")
	@Expose
	private String productCategory;
	
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
	
	public String getBreeder() {
		return breeder;
	}
	
	public void setBreeder(String breeder) {
		this.breeder = breeder;
	}
	
	public String getProductType() {
		return productType;
	}
	
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	public String getParentProduct() {
		return parentProduct;
	}
	
	public void setParentProduct(String parentProduct) {
		this.parentProduct = parentProduct;
	}
	
	public String getParentProductCode() {
		return parentProductCode;
	}
	
	public void setParentProductCode(String parentProductCode) {
		this.parentProductCode = parentProductCode;
	}
	
	public String getProductCategory() {
		return productCategory;
	}
	
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
}
