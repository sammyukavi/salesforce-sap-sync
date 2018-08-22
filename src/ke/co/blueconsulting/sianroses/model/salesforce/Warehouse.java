package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.data.adapter.EmptyStringTypeAdapter;

import java.io.Serializable;
//One way push to salesforce

/**
 * This model maps SAP Warehouse to Salesforce Warehouse.
 * Syncing for warehouses is a one way push from SAP to Salesforce.
 */
@DatabaseTable(tableName = "WAREHOUSE")
public class Warehouse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("ERP_ID__c")
	@Expose
	private int autoId;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	@JsonAdapter(EmptyStringTypeAdapter.class)
	private String SalesForceId;
	
	@DatabaseField(columnName = "Farm__c")
	//@SerializedName("Farm__c")
	@SerializedName("Farm_SAP__c")
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
