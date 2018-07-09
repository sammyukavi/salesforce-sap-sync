package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "PRICELIST")
public class PriceList {
	
	@DatabaseField(generatedId = true, columnName = "AUTOID")
	private int autoId;
	
	@SerializedName("Id")
	private String id;
	
	@SerializedName("Pricebook2Id")
	@DatabaseField(columnName = "Pricebook2Id")
	private String pricebook2Id;
	
	@SerializedName("Product2Id")
	@DatabaseField(columnName = "Product2Id")
	private String product2Id;
	
	@DatabaseField(columnName = "CustomerCode")
	private String customerCode;
	
	@DatabaseField(columnName = "productCode")
	private String productCode;
	
	@SerializedName("UnitPrice")
	@DatabaseField(columnName = "UnitPrice")
	private double unitPrice;
	
	@DatabaseField(columnName = "CurrencyISOCode")
	@SerializedName("CurrencyIsoCode")
	private String currencyISOCode;
	
	@SerializedName("IsActive")
	@DatabaseField(columnName = "isActive")
	private boolean isActive;
	
	
}