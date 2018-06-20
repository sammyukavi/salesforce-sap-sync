package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "ARINVOICE")
public class ArInvoice {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int id;
  
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
  
  @DatabaseField(columnName = "Farm_Order_Number__c")
  private String farmOrderNumber;
  
  @DatabaseField(columnName = "Invoice_Number__c")
  private String invoiceNumber;
  
  @DatabaseField(columnName = "Invoice_Entry__c")
  private String invoiceEntry;
  
  @DatabaseField(columnName = "Farm_c")
  private String farmC;
  
  @DatabaseField(columnName = "Auction")
  private String auction;
  
  @DatabaseField(columnName = "Reason")
  private String reason;
  
  @DatabaseField(columnName = "Push_to_SAP__c")
  private Character pushToSap;
  
  @DatabaseField(columnName = "Pull_from_SAP__c")
  private Character pullFromSap;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
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
  
  public Character getPushToSap() {
    return pushToSap;
  }
  
  public void setPushToSap(Character pushToSap) {
    this.pushToSap = pushToSap;
  }
  
  public Character getPullFromSap() {
    return pullFromSap;
  }
  
  public void setPullFromSap(Character pullFromSap) {
    this.pullFromSap = pullFromSap;
  }
  
  public String getFarmOrderNumber() {
    return farmOrderNumber;
  }
  
  public void setFarmOrderNumber(String farmOrderNumber) {
    this.farmOrderNumber = farmOrderNumber;
  }
  
  public String getAuction() {
    return auction;
  }
  
  public void setAuction(String auction) {
    this.auction = auction;
  }
}
