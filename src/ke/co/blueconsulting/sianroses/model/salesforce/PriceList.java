package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.data.adapter.EmptyStringTypeAdapter;

import java.io.Serializable;

//One way push from SAP to salesforce

/**
 * This model maps price lists to PriceBookEntry in Salesforce.
 * Syncing is one way.
 */
@DatabaseTable(tableName = "PRICELIST")
public class PriceList implements Serializable {
	
	private static final long serialVersionUID = 193639623594050L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	@SerializedName("Auto_Id__c")
	@Expose
	private int autoId;
	
	@DatabaseField(columnName = "SalesForceId")
	@SerializedName("Id")
	@Expose
	@JsonAdapter(EmptyStringTypeAdapter.class)
	private String SalesForceId;
	
	/*@DatabaseField(columnName = "Pricebook2Id")
	@SerializedName("Pricebook2Id")
	@Expose
	private String pricebook2Id;
	
	@DatabaseField(columnName = "Product2Id")
	@SerializedName("Product2Id")
	@Expose
	private String product2Id;*/
	
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
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
	@DatabaseField(columnName = "Push_to_SAP__c")
	@SerializedName("Push_to_SAP__c")
	@Expose
	private boolean pushToSAP;
	
	public int getAutoId() {
		return autoId;
	}
	
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getSalesForceId() {
		return SalesForceId;
	}
	
	public void setSalesForceId(String salesForceId) {
		SalesForceId = salesForceId;
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
	
	public boolean isPullFromSAPC() {
		return pullFromSAPC;
	}
	
	public void setPullFromSAPC(boolean pullFromSAPC) {
		this.pullFromSAPC = pullFromSAPC;
	}
	
	public boolean isPushToSAP() {
		return pushToSAP;
	}
	
	public void setPushToSAP(boolean pushToSAP) {
		this.pushToSAP = pushToSAP;
	}
}
