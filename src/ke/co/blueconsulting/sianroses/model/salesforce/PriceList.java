package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRICELIST")
public class PriceList {
	
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
	
	
}