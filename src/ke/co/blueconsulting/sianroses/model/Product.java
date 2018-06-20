package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRODUCTS")
public class Product {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "productcode")
  private String productCode;
  
  @DatabaseField(columnName = "Name")
  private String name;
  
  @DatabaseField(columnName = "Color__c")
  private String color;
  
  @DatabaseField(columnName = "Classification__c")
  private String classification;
  
  @DatabaseField(columnName = "IsActive")
  private String isActive;
  
  @DatabaseField(columnName = "Breeder__c")
  private String breeder;
  
  @DatabaseField(columnName = "Family")
  private String family;
  
  @DatabaseField(columnName = "Product_Type__c", canBeNull = false)
  private String productType;
  
  @DatabaseField(columnName = "Parent_Product__c")
  private String parentProduct;
  
  @DatabaseField(columnName = "Parent_Product_Code__c")
  private String ParentProductCode;
  
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
  
  public String getProductCode() {
    return productCode;
  }
  
  public void setProductCode(String productCode) {
    this.productCode = productCode;
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
  
  public String getFamily() {
    return family;
  }
  
  public void setFamily(String family) {
    this.family = family;
  }
  
  public String getProductType() {
    return productType;
  }
  
  public void setProductType(String productType) {
    this.productType = productType;
  }
  
  public String getParentProduct() {
    return parentProduct;
  }
  
  public void setParentProduct(String parentProduct) {
    this.parentProduct = parentProduct;
  }
  
  public String getParentProductCode() {
    return ParentProductCode;
  }
  
  public void setParentProductCode(String parentProductCode) {
    ParentProductCode = parentProductCode;
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