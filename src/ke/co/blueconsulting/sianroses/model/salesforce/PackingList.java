package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;
import java.util.Date;

/**
 * This model maps Salesforce Packing List to SAP ARINVOICE Table. Syncing is 2 ways from Salesforce to SAP
 */
@DatabaseTable(tableName = "ARINVOICE")
public class PackingList extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 1527097474049L;
	
	@DatabaseField(columnName = "AccountID")
	@SerializedName("Customer_Name__c")
	@Expose
	private String AccountID;
	
	@DatabaseField(columnName = "Posting_Date__c")
	@SerializedName("CreatedDate")
	private Date postingDateC;
	
	@DatabaseField(columnName = "Due_Date__c")
	@SerializedName("Due_Date__c")
	@Expose
	private String dueDateC;
	
	@DatabaseField(columnName = "PO_Number__c")
	@SerializedName("PO_Number__c")
	@Expose
	private String pONumberC;
	
	@DatabaseField(columnName = "Farm_Order_Number__c")
	@SerializedName("Name")
	@Expose
	private String farmOrderNumberC;
	
	@DatabaseField(columnName = "Invoice_Number__c")
	@SerializedName("Invoice_No__c")
	@Expose
	private String invoiceNumberC;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice__c")
	@Expose
	private String InvoiceEntryC;
	
	@DatabaseField(columnName = "Farm_c")
	@SerializedName("Farm__c")
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
	
	@SerializedName("Packing_List_Items__r")
	private PackingListItems packingListItems;
	
	
	public String getAccountID() {
		return AccountID;
	}
	
	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	
	public Date getPostingDateC() {
		return postingDateC;
	}
	
	public void setPostingDateC(Date postingDateC) {
		this.postingDateC = postingDateC;
	}
	
	public String getDueDateC() {
		return dueDateC;
	}
	
	public void setDueDateC(String dueDateC) {
		this.dueDateC = dueDateC;
	}
	
	public String getpONumberC() {
		return pONumberC;
	}
	
	public void setpONumberC(String pONumberC) {
		this.pONumberC = pONumberC;
	}
	
	public String getFarmOrderNumberC() {
		return farmOrderNumberC;
	}
	
	public void setFarmOrderNumberC(String farmOrderNumberC) {
		this.farmOrderNumberC = farmOrderNumberC;
	}
	
	public String getInvoiceNumberC() {
		return invoiceNumberC;
	}
	
	public void setInvoiceNumberC(String invoiceNumberC) {
		this.invoiceNumberC = invoiceNumberC;
	}
	
	public String getInvoiceEntryC() {
		return InvoiceEntryC;
	}
	
	public void setInvoiceEntryC(String invoiceEntryC) {
		InvoiceEntryC = invoiceEntryC;
	}
	
	public String getFarmC() {
		return farmC;
	}
	
	public void setFarmC(String farmC) {
		this.farmC = farmC;
	}
	
	public String getAuction() {
		return auction;
	}
	
	public void setAuction(String auction) {
		this.auction = auction;
	}
	
	public String getReason() {
		return Reason;
	}
	
	public void setReason(String reason) {
		Reason = reason;
	}
	
	public PackingListItems getPackingListItems() {
		return packingListItems;
	}
	
	public void setPackingListItems(PackingListItems packingListItems) {
		this.packingListItems = packingListItems;
	}
}
