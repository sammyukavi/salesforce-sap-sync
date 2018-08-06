package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PackingListItems {
	
	@SerializedName("totalSize")
	@Expose
	private int totalSize;
	
	@SerializedName("done")
	@Expose
	private boolean done;
	
	@SerializedName("records")
	@Expose
	private ArrayList<Item> items;
	
}
