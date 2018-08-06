package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "ARINVOICECH")
public class ArInvoiceCh implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(columnName = "AUTOID", generatedId = true)
	@SerializedName("ERP_ID__c")
	@Expose
	private int autoId;
	
	@DatabaseField(columnName = "AUTOIDMST")
	@SerializedName("AUTOIDMST")
	@Expose
	private int autoIdMst;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	private String SalesForceId;
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCodeC;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Flower_Name__c")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Quantity__c")
	@SerializedName("Quantity__c")
	@Expose
	private String quantityC;
	
	@DatabaseField(columnName = "Warehouse__c")
	@SerializedName("Warehouse__c")
	@Expose
	private String warehouseC;
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("List_Price__c")
	@Expose
	private String unitPrice;
	
	@DatabaseField(columnName = "Tax_Code__c")
	@SerializedName("Tax_Code__c")
	@Expose
	private String taxCodeC;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice_Entry__c")
	@Expose
	private String InvoiceEntryC;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC = false;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC = false;
	
}
