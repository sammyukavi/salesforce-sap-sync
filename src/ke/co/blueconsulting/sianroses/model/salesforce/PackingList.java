package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.SerializedName;

public class PackingList {
	
	@SerializedName("Id")
	private String id;
	
	@SerializedName("Consignment_No__c")
	private String consignmentNoC;
	
	@SerializedName("Farm__c")
	private String farmC;
	
	@SerializedName("Customer_Name__c")
	private String Customer_Name__c;
	
	@SerializedName("PO_Number__c")
	private String PO_Number__c;
	
	@SerializedName("CreatedDate")
	private String createdDate;
	
	@SerializedName("Packing_List_Items__r")
	private PackingListItems packingListItems;
}