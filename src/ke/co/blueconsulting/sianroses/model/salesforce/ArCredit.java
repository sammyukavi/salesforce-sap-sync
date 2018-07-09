package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

/**
 * Unable to find Object on salesforce
 */
@DatabaseTable(tableName = "ARCREDIT")
public class ArCredit implements Serializable {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("ERP_ID__c")
	@Expose
	private int eRPIdC;
	
	@DatabaseField(columnName = "AccountID")
	@SerializedName("AccountId")
	@Expose
	private String accountId;
	
	@DatabaseField(columnName = "Posting_Date__c")
	@SerializedName("Posting_Date__c")
	@Expose
	private Date postingDateC;
	
	@DatabaseField(columnName = "Due_Date__c")
	@SerializedName("Due_Date__c")
	@Expose
	private Date dueDateC;
	
	@DatabaseField(columnName = "PO_Number__c")
	@SerializedName("PO_Number__c")
	@Expose
	private String pONumberC;
	
	@DatabaseField(columnName = "Invoice_Number__c")
	@SerializedName("Invoice_Number__c")
	@Expose
	private String invoiceNumberC;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice_Entry__c")
	@Expose
	private String invoiceEntryC;
	
	@DatabaseField(columnName = "Farm_c")
	@SerializedName("Farm_c")
	@Expose
	private String farmC;
	
	@DatabaseField(columnName = "Reason")
	@SerializedName("Reason")
	@Expose
	private String reason;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSap;
	
}
