package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "STOCKMANAGEMENT")
public class StockManagement {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "Flower_Code__c")
  private String flowerCode;
  
  @DatabaseField(columnName = "Name")
  private String name;
  
  @DatabaseField(columnName = "Warehouse__c")
  private String warehouse;
  
  @DatabaseField(columnName = "Quantity__c")
  private double quantity;
  
  @DatabaseField(columnName = "Farm_Code__c")
  private String farmCode;
  
  @DatabaseField(columnName = "Farm__c")
  private String farm;
  
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
  
  public String getFlowerCode() {
    return flowerCode;
  }
  
  public void setFlowerCode(String flowerCode) {
    this.flowerCode = flowerCode;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getWarehouse() {
    return warehouse;
  }
  
  public void setWarehouse(String warehouse) {
    this.warehouse = warehouse;
  }
  
  public double getQuantity() {
    return quantity;
  }
  
  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }
  
  public String getFarmCode() {
    return farmCode;
  }
  
  public void setFarmCode(String farmCode) {
    this.farmCode = farmCode;
  }
  
  public String getFarm() {
    return farm;
  }
  
  public void setFarm(String farm) {
    this.farm = farm;
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
