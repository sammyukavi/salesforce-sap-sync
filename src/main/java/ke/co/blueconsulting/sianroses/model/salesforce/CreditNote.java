package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;
import java.util.Date;

/**
 * This model maps Salesforce Packing List to SAP. Syncing is 2 ways from Salesforce to SAP
 */
@DatabaseTable(tableName = "ARCREDIT")
public class CreditNote extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 1527097474049L;
	
	@DatabaseField(columnName = "AccountID")
	@SerializedName("Customer_Name__c")
	@Expose
	private String accountId;
	
	@DatabaseField(columnName = "Posting_Date__c")
	@SerializedName("CreatedDate")
	private Date postingDate;
	
	@DatabaseField(columnName = "Due_Date__c")
	@SerializedName("Due_Date__c")
	@Expose
	private String dueDate;
	
	@DatabaseField(columnName = "PO_Number__c")
	@SerializedName("PO_Number__c")
	@Expose
	private String pONumber;
	
	@DatabaseField(columnName = "Farm_Order_Number__c")
	@SerializedName("Name")
	@Expose
	private String farmOrderNumber;
	
	@DatabaseField(columnName = "Invoice_Number__c")
	@SerializedName("Invoice_No__c")
	@Expose
	private String invoiceNumber;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice__c")
	@Expose
	private String invoiceEntry;
	
	@DatabaseField(columnName = "Farm_c")
	@SerializedName("Farm__c")
	@Expose
	private String farm;
	
	@DatabaseField(columnName = "Auction")
	@SerializedName("Auction")
	@Expose
	private String auction;
	
	@DatabaseField(columnName = "Reason")
	@SerializedName("Reason")
	@Expose
	private String reason;
	
	@SerializedName("Packing_List_Items__r")
	private PackingListItems packingListItems;
	
	
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
	
	public String getDueDate() {
		return dueDate;
	}
	
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
	public String getPONumber() {
		return pONumber;
	}
	
	public void setPONumber(String pONumber) {
		this.pONumber = pONumber;
	}
	
	public String getFarmOrderNumber() {
		return farmOrderNumber;
	}
	
	public void setFarmOrderNumber(String farmOrderNumber) {
		this.farmOrderNumber = farmOrderNumber;
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
	
	public String getFarm() {
		return farm;
	}
	
	public void setFarm(String farm) {
		this.farm = farm;
	}
	
	public String getAuction() {
		return auction;
	}
	
	public void setAuction(String auction) {
		this.auction = auction;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public PackingListItems getPackingListItems() {
		return packingListItems;
	}
	
	public void setPackingListItems(PackingListItems packingListItems) {
		this.packingListItems = packingListItems;
	}
}
