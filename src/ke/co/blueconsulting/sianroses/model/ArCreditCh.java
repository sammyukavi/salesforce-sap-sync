package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ARCREDITCH")
public class ArCreditCh {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "AUTOIDMST")
  private int autoIdMst;
  
  @DatabaseField(columnName = "Flower_Code__c")
  private String flowerCodeC;
  
  @DatabaseField(columnName = "Name")
  private String name;
  
  @DatabaseField(columnName = "Quantity__c")
  private double quantity;
  
  @DatabaseField(columnName = "Warehouse__c")
  private String warehouse;
  
  @DatabaseField(columnName = "UnitPrice")
  private double unitPrice;
  
  @DatabaseField(columnName = "Tax__c")
  private String taxCode;
  
  @DatabaseField(columnName = "Invoice_Entry__c")
  private String invoiceEntry;
  
  @DatabaseField(columnName = "Push_to_SAP__c")
  private Character pushToSap;
  
  @DatabaseField(columnName = "Pull_from_SAP__c")
  private Character pullFromSap;
  
  public int getAutoId() {
    return autoId;
  }
  
  public void setAutoId(int autoId) {
    this.autoId = autoId;
  }
  
  public int getAutoIdMst() {
    return autoIdMst;
  }
  
  public void setAutoIdMst(int autoIdMst) {
    this.autoIdMst = autoIdMst;
  }
  
  public String getFlowerCodeC() {
    return flowerCodeC;
  }
  
  public void setFlowerCodeC(String flowerCodeC) {
    this.flowerCodeC = flowerCodeC;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public double getQuantity() {
    return quantity;
  }
  
  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
  
  public String getWarehouse() {
    return warehouse;
  }
  
  public void setWarehouse(String warehouse) {
    this.warehouse = warehouse;
  }
  
  public double getUnitPrice() {
    return unitPrice;
  }
  
  public void setUnitPrice(double unitPrice) {
    this.unitPrice = unitPrice;
  }
  
  public String getTaxCode() {
    return taxCode;
  }
  
  public void setTaxCode(String taxCode) {
    this.taxCode = taxCode;
  }
  
  public String getInvoiceEntry() {
    return invoiceEntry;
  }
  
  public void setInvoiceEntry(String invoiceEntry) {
    this.invoiceEntry = invoiceEntry;
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
}
