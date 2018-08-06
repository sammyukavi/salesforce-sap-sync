package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {
	
	@SerializedName("Id")
	@Expose
	private String id;
	
	@SerializedName("Flower_Code__c")
	@Expose
	private String flowerCodeC;
	
	@SerializedName("Flower_Name__c")
	@Expose
	private String flowerNameC;
	
	@SerializedName("List_Price__c")
	@Expose
	private String listPriceC;
	
	@SerializedName("Quantity__c")
	@Expose
	private int quantityC;
	
	
}
