package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * @author sukavi
 * This model maps Salesforce Packing List to SAP ARINVOICE Table
 */
@DatabaseTable(tableName = "ARINVOICE")
public class ArInvoice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(columnName = "AUTOID", generatedId = true)
	@SerializedName("ERP_ID__c")
	@Expose
	private int autoId;
	
	@DatabaseField(columnName = "SalesforceId")
	@SerializedName("Id")
	@Expose
	private String salesforceId;
	
	@DatabaseField(columnName = "AccountID")
	@SerializedName("Customer_Name__c")
	@Expose
	private String AccountID;
	
	@DatabaseField(columnName = "Posting_Date__c")
	@SerializedName("CreatedDate")
	@Expose
	private String postingDateC;
	
	@DatabaseField(columnName = "Due_Date__c")
	@SerializedName("Due_Date__c")
	@Expose
	private String dueDateC;
	
	@DatabaseField(columnName = "PO_Number__c")
	@SerializedName("PO_Number__c")
	@Expose
	private String pONumberC;
	
	/*@DatabaseField(columnName = "Farm_Order_Number__c")
	@SerializedName("Consignment_No__c")
	@Expose
	private String farmOrderNumberC;*/
	
	@DatabaseField(columnName = "Invoice_Number__c")
	@SerializedName("Invoice_Number__c")
	@Expose
	private String invoiceNumberC;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice_Entry__c")
	@Expose
	private String InvoiceEntryC;
	
	@DatabaseField(columnName = "Farm_c")
	@SerializedName("Farm_c")
	@Expose
	private String farmC;
	
	@DatabaseField(columnName = "Auction")
	@SerializedName("Auction")
	@Expose
	private String auction;
	
	@DatabaseField(columnName = "Reason")
	@SerializedName("Reason")
	@Expose
	private String Reason;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC = false;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAPC = false;
	
}
