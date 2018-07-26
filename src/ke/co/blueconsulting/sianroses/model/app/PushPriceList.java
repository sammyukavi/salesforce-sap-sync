package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;

import java.io.Serializable;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRICElIST;

public class PushPriceList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName(PRICElIST)
	@Expose
	private ArrayList<PriceList> priceList;
	
	public PushPriceList(ArrayList<PriceList> priceList) {
		this.priceList = priceList;
	}
	
	public ArrayList<PriceList> getPriceList() {
		return priceList;
	}
	
}
