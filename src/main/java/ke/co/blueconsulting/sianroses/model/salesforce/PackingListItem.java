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
@DatabaseTable(tableName = "ARINVOICECH")
public class PackingListItem extends BaseSalesforceModel implements Serializable {
	
	private static final long serialVersionUID = 127597320235238L;
	
	@DatabaseField(columnName = "AUTOIDMST")
	private int autoIdMst;
	
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
	
	@DatabaseField(columnName = "Warehouse__c")
	private String warehouse;
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("List_Price__c")
	@Expose
	private String unitPrice;
	
	@DatabaseField(columnName = "Tax_Code__c")
	private String taxCodeC;
	
	@DatabaseField(columnName = "Invoice_Entry__c")
	private String InvoiceEntryC;
	
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
	
	public int getAutoIdMst() {
		return autoIdMst;
	}
	
	public void setAutoIdMst(int autoIdMst) {
		this.autoIdMst = autoIdMst;
	}
	
	public String getWarehouse() {
		return warehouse;
	}
	
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	public String getTaxCodeC() {
		return taxCodeC;
	}
	
	public void setTaxCodeC(String taxCodeC) {
		this.taxCodeC = taxCodeC;
	}
	
	public String getInvoiceEntryC() {
		return InvoiceEntryC;
	}
	
	public void setInvoiceEntryC(String invoiceEntryC) {
		InvoiceEntryC = invoiceEntryC;
	}
}
