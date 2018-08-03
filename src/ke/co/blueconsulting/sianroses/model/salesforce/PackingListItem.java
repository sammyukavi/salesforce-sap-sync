package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackingListItem {
	
	@SerializedName("Id")
	@Expose
	private int id;
	
	@SerializedName("Flower_Code__c")
	@Expose
	private int flowerCodeC;
	
	@SerializedName("Flower_Name__c")
	@Expose
	private int flowerNameC;
	
	@SerializedName("List_Price__c")
	@Expose
	private int listPriceC;
	
	@SerializedName("Quantity__c")
	@Expose
	private int quantityC;
	
	
}
