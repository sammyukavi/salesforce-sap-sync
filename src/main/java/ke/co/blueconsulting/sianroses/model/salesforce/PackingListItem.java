package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;

import java.io.Serializable;

/**
 * This model maps Salesforce Packing List Items to SAP . Syncing is 2 ways from Salesforce to SAP
 */
@DatabaseTable(tableName = "ARCREDITCH")
public class PackingListItem extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 127597320235238L;
	
	/*@DatabaseField(columnName = "AUTOIDMST")
	@SerializedName("AUTOIDMST")
	@Expose
	private int autoIdMst;*/
	
	@DatabaseField(columnName = "Flower_Code__c")
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCode;
	
	@DatabaseField(columnName = "Name")
	@SerializedName("Flower_Name__c")
	@Expose
	private String name;
	
	@DatabaseField(columnName = "Quantity__c")
	@SerializedName("Quantity__c")
	@Expose
	private String quantity;
	
	/*@DatabaseField(columnName = "Warehouse__c")
	@SerializedName("Warehouse__c")
	@Expose
	private String warehouse;*/
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("List_Price__c")
	@Expose
	private String unitPrice;
	
	/*@DatabaseField(columnName = "Tax_Code__c")
	@SerializedName("Tax_Code__c")
	@Expose
	private String taxCodeC;*/
	
	/*@DatabaseField(columnName = "Invoice_Entry__c")
	@SerializedName("Invoice_Entry__c")
	@Expose
	private String InvoiceEntryC;*/
	
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
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getUnitPrice() {
		return unitPrice;
	}
	
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
}
