package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRICELIST")
public class PriceList {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "PaymentTermCode")
  private String paymentTermCode;
  
  @DatabaseField(columnName = "Pricebook2Id")
  private String pricebook2Id;
  
  @DatabaseField(columnName = "Product2Id")
  private String product2Id;
  
  @DatabaseField(columnName = "CustomerCode")
  private String customerCode;
  
  @DatabaseField(columnName = "productCode")
  private String productCode;
  
  @DatabaseField(columnName = "UnitPrice")
  private boolean unitPrice;
  
  @DatabaseField(columnName = "CurrencyISOCode")
  private String currencyISOCode;
  
  @DatabaseField(columnName = "isActive")
  private String isActive;
  
  public int getAutoId() {
    return autoId;
  }
  
  public void setAutoId(int autoId) {
    this.autoId = autoId;
  }
  
  public String getPaymentTermCode() {
    return paymentTermCode;
  }
  
  public void setPaymentTermCode(String paymentTermCode) {
    this.paymentTermCode = paymentTermCode;
  }
  
  public String getPricebook2Id() {
    return pricebook2Id;
  }
  
  public void setPricebook2Id(String pricebook2Id) {
    this.pricebook2Id = pricebook2Id;
  }
  
  public String getProduct2Id() {
    return product2Id;
  }
  
  public void setProduct2Id(String product2Id) {
    this.product2Id = product2Id;
  }
  
  public String getCustomerCode() {
    return customerCode;
  }
  
  public void setCustomerCode(String customerCode) {
    this.customerCode = customerCode;
  }
  
  public String getProductCode() {
    return productCode;
  }
  
  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }
  
  public boolean isUnitPrice() {
    return unitPrice;
  }
  
  public void setUnitPrice(boolean unitPrice) {
    this.unitPrice = unitPrice;
  }
  
  public String getCurrencyISOCode() {
    return currencyISOCode;
  }
  
  public void setCurrencyISOCode(String currencyISOCode) {
    this.currencyISOCode = currencyISOCode;
  }
  
  public String getIsActive() {
    return isActive;
  }
  
  public void setIsActive(String isActive) {
    this.isActive = isActive;
  }
}