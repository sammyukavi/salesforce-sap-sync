package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

//
@DatabaseTable(tableName = "ARCREDIT")
public class ArCredit implements Serializable {
	
	@SerializedName("AUTOID")
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@DatabaseField(columnName = "ERP_ID__c")
	private String erpId;
	
	@DatabaseField(columnName = "AccountID")
	private String accountId;
	
	@DatabaseField(columnName = "Posting_Date__c")
	private Date postingDate;
	
	@DatabaseField(columnName = "Due_Date__c")
	private Date dueDate;
	
	@DatabaseField(columnName = "PO_Number__c")
	private String pONumber;
	
	@DatabaseField(columnName = "Invoice_Number__c")
	private String invoiceNumber;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	private String invoiceEntry;
	
	@DatabaseField(columnName = "Farm_c")
	private String farmC;
	
	@DatabaseField(columnName = "Reason")
	private String reason;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	private boolean pushToSap;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	private boolean pullFromSap;
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getErpId() {
		return erpId;
	}
	
	public void setErpId(String erpId) {
		this.erpId = erpId;
	}
	
	public String getAccountId() {
		return accountId;
	}
	
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public Date getPostingDate() {
		return postingDate;
	}
	
	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getpONumber() {
		return pONumber;
	}
	
	public void setpONumber(String pONumber) {
		this.pONumber = pONumber;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public String getInvoiceEntry() {
		return invoiceEntry;
	}
	
	public void setInvoiceEntry(String invoiceEntry) {
		this.invoiceEntry = invoiceEntry;
	}
	
	public String getFarmC() {
		return farmC;
	}
	
	public void setFarmC(String farmC) {
		this.farmC = farmC;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
