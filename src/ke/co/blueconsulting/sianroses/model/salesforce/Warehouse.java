package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WAREHOUSE")
public class Warehouse {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("AUTOID")
	@Expose
	private int autoId;
	
	@SerializedName("Id")
	private String id;
	
	@DatabaseField(columnName = "Farm__c")
	@SerializedName("Farm__c")
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
	
	
}
