package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;

import java.io.Serializable;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.PRODUCTS;

public class PushProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName(PRODUCTS)
	@Expose
	private ArrayList<Product> Product;
	
	public PushProduct(ArrayList<Product> Product) {
		this.Product = Product;
	}
	
	public ArrayList<Product> getProduct() {
		return Product;
	}
	
}
