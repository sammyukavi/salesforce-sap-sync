package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;


/**
 * This model maps price lists to PriceBookEntry in Salesforce.
 * Syncing is one way.
 */
@DatabaseTable(tableName = "PRICELIST")
public class PriceList extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 193639623594050L;
	
	@DatabaseField(columnName = "CustomerCode")
	@SerializedName("CustomerCode__c")
	@Expose
	private String customerCode;
	
	@DatabaseField(columnName = "productCode")
	@SerializedName("ProductCode__c")
	@Expose
	private String productCode;
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("UnitPrice__c")
	@Expose
	private double unitPrice;
	
	@DatabaseField(columnName = "CurrencyISOCode")
	@SerializedName("CurrencyIsoCode")
	@Expose
	private String currencyISOCode;
	
	@DatabaseField(columnName = "isActive")
	@SerializedName("isActive__c")
	@Expose
	private boolean isActive;
	
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
	
	public double getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public String getCurrencyISOCode() {
		return currencyISOCode;
	}
	
	public void setCurrencyISOCode(String currencyISOCode) {
		this.currencyISOCode = currencyISOCode;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean active) {
		isActive = active;
	}
	
	
}
