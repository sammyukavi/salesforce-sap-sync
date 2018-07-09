package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "WAREHOUSE")
public class Warehouse {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@SerializedName("Id")
	private String id;
	
	@SerializedName("Warehouse_Code__c")
	@DatabaseField(columnName = "Warehouse_Code__c")
	private String warehouseCodeC;
	
	@SerializedName("Name")
	@DatabaseField(columnName = "Name")
	private String name;
	
	@DatabaseField(columnName = "Farm_Code__c")
	private String farmCodeC;
	
	@SerializedName("Farm__c")
	@DatabaseField(columnName = "Farm__c")
	private String farmC;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC;
	
	
}
