package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.*;

import java.io.Serializable;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.*;

/**
 * A conversion class used to send data to Salesforce server and back to the SAP
 * server
 */
public class Response implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName(ACCOUNTS)
	@Expose
	private ArrayList<Customer> customers = new ArrayList<>();
	
	@SerializedName(CONTACTS)
	@Expose
	private ArrayList<CustomerContacts> customerContacts = new ArrayList<>();
	
	@SerializedName(PRICElIST)
	@Expose
	private ArrayList<PriceList> priceList = new ArrayList<>();
	
	@SerializedName(PRODUCTS)
	@Expose
	private ArrayList<Product> products = new ArrayList<>();
	
	@SerializedName(PRODUCTSCHILDREN)
	@Expose
	private ArrayList<ProductChild> productsChildren = new ArrayList<>();
	
	@SerializedName("warehouses")
	@Expose
	private ArrayList<Warehouse> warehouses = new ArrayList<>();
	
	public Response(ArrayList<ProductChild> productsChildren) {
		this.productsChildren.clear();
		this.productsChildren.addAll(productsChildren);
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public ArrayList<CustomerContacts> getCustomerContacts() {
		return customerContacts;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
