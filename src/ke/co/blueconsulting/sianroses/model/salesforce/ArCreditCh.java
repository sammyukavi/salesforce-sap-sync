package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Unable to find Object on salesforce
 */
@DatabaseTable(tableName = "ARCREDITCH")
public class ArCreditCh {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@DatabaseField(columnName = "AUTOIDMST")
	private int autoIdMst;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCodeC;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Name")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Quantity__c")
	@SerializedName("Quantity__c")
	@Expose
	private double quantity;
	
	@DatabaseField(columnName = "Warehouse__c")
	@SerializedName("Warehouse__c")
	@Expose
	private String warehouse;
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("UnitPrice")
	@Expose
	private double unitPrice;
	
	@DatabaseField(columnName = "Tax__c")
	@SerializedName("Tax__c")
	@Expose
	private String taxCode;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice_Entry__c")
	@Expose
	private String invoiceEntry;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSap;
	
	
}
