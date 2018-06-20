package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PAYMENTTERMS")
public class PaymentTerm {
  
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int autoId;
  
  @DatabaseField(columnName = "PaymentTermCode")
  private String paymentTermCode;
  
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
}
