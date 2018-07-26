package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

//One way push from SAP to salesforce

/**
 * This model maps price lists to PriceBookEntry in Salesforce Syncing is one way.
 */
@DatabaseTable(tableName = "PRICELIST")
public class PriceList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@SerializedName("Id")
	@Expose
	private String id;
	
	@DatabaseField(columnName = "Pricebook2Id")
	@SerializedName("Pricebook2Id")
	@Expose
	private String pricebook2Id;
	
	@DatabaseField(columnName = "Product2Id")
	@SerializedName("Product2Id")
	@Expose
	private String product2Id;
	
	@DatabaseField(columnName = "CustomerCode")
	@SerializedName("CustomerCode")
	@Expose
	private String customerCode;
	
	@DatabaseField(columnName = "productCode")
	@SerializedName("ProductCode")
	@Expose
	private String productCode;
	
	@DatabaseField(columnName = "UnitPrice")
	@SerializedName("UnitPrice")
	@Expose
	private double unitPrice;
	
	@DatabaseField(columnName = "CurrencyISOCode")
	@SerializedName("CurrencyIsoCode")
	@Expose
	private String currencyISOCode;
	
	@DatabaseField(columnName = "isActive")
	@SerializedName("IsActive")
	@Expose
	private boolean isActive;
	
	@DatabaseField(columnName = "Pull_from_SAP__c")
	@SerializedName("Pull_from_SAP__c")
	@Expose
	private boolean pullFromSAPC;
	
}
