package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This model maps Products to Products2 in Salesforce
 * Syncing for Products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTS")
public class Product {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@DatabaseField(columnName = "Breeder__c")
	@SerializedName("Breeder__c")
	@Expose
	private String breeder;
	
	@DatabaseField(columnName = "Classification__c")
	@SerializedName("Classification__c")
	@Expose
	private String classification;
	
	@DatabaseField(columnName = "Color__c")
	@SerializedName("Color__c")
	@Expose
	private String color;
	
	@DatabaseField(columnName = "Family")
	@SerializedName("Family")
	@Expose
	private String family;
	
	@DatabaseField(columnName = "IsActive")
	@SerializedName("IsActive")
	@Expose
	private String isActive;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Parent_Product__c")
	@SerializedName("Parent_Product__c")
	@Expose
	private String parentProduct;
	
	@DatabaseField(columnName = "Parent_Product_Code__c")
	@SerializedName("Parent_Product_Code__c")
	@Expose
	private String ParentProductCode;
	
	@DatabaseField(columnName = "Product_Type__c")
	@SerializedName("RecordTypeId")
	@Expose
	private String productTypeC;
	
	@DatabaseField(columnName = "ProductCode")
	@SerializedName("ProductCode")
	@Expose
	private String productCode;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC;
	
}