package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRODUCTSCHILD")
public class ProductChild {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "Flower_Code__c")
  private String flowerCode;
  
  @DatabaseField(columnName = "Name")
  private String name;
  
  @DatabaseField(columnName = "Color__c")
  private String color;
  
  @DatabaseField(columnName = "Classification__c")
  private String classification;
  
  @DatabaseField(columnName = "Is_Active__c")
  private String isActive;
  
  @DatabaseField(columnName = "Breeder__c")
  private String breeder;
  
  @DatabaseField(columnName = "Product_Family__c")
  private String productFamily;
  
  @DatabaseField(columnName = "Farm__c")
  private String farm;
  
  @DatabaseField(columnName = "Headsize__c")
  private String headSize;
  
  @DatabaseField(columnName = "Consumable_Stock__c")
  private String consumableStock;
  
  @DatabaseField(columnName = "Length__c")
  private String length;
  
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
  
  public String getColor() {
    return color;
  }
  
  public void setColor(String color) {
    this.color = color;
  }
  
  public String getClassification() {
    return classification;
  }
  
  public void setClassification(String classification) {
    this.classification = classification;
  }
  
  public String getIsActive() {
    return isActive;
  }
  
  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
  
  public String getBreeder() {
    return breeder;
  }
  
  public void setBreeder(String breeder) {
    this.breeder = breeder;
  }
  
  public String getProductFamily() {
    return productFamily;
  }
  
  public void setProductFamily(String productFamily) {
    this.productFamily = productFamily;
  }
  
  public String getFarm() {
    return farm;
  }
  
  public void setFarm(String farm) {
    this.farm = farm;
  }
  
  public String getHeadSize() {
    return headSize;
  }
  
  public void setHeadSize(String headSize) {
    this.headSize = headSize;
  }
  
  public String getConsumableStock() {
    return consumableStock;
  }
  
  public void setConsumableStock(String consumableStock) {
    this.consumableStock = consumableStock;
  }
  
  public String getLength() {
    return length;
  }
  
  public void setLength(String length) {
    this.length = length;
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