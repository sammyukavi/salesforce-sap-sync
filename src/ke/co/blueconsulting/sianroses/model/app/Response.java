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
	
	private static Response instance;
	
	static {
		instance = new Response();
	}
	
	@SerializedName(ACCOUNTS)
	@Expose
	private ArrayList<Customer> customers = new ArrayList<>();
	@SerializedName(CONTACTS)
	@Expose
	private ArrayList<CustomerContacts> customerContacts = new ArrayList<>();
	@SerializedName(PRICE_LIST)
	@Expose
	private ArrayList<PriceList> priceList = new ArrayList<>();
	@SerializedName(PRODUCTS)
	@Expose
	private ArrayList<Product> products = new ArrayList<>();
	@SerializedName(PRODUCTS_CHILDREN)
	@Expose
	private ArrayList<ProductChild> productsChildren = new ArrayList<>();
	@SerializedName(WAREHOUSES)
	@Expose
	private ArrayList<Warehouse> warehouses = new ArrayList<>();
	
	public Response() {
	
	}
	
	public static Response setPriceList(ArrayList<PriceList> priceList) {
		instance.priceList = priceList;
		return instance;
	}
	
	public static Response setProductsChildren(ArrayList<ProductChild> productsChildren) {
		instance.productsChildren = productsChildren;
		return instance;
	}
	
	public static Response setProducts(ArrayList<Product> products) {
		instance.products = products;
		return instance;
	}
	
	public static Response setWarehouses(ArrayList<Warehouse> warehouses) {
		instance.warehouses = warehouses;
		return instance;
	}
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public static Response setCustomers(ArrayList<Customer> customers) {
		instance.customers = customers;
		return instance;
	}
	
	public ArrayList<CustomerContacts> getCustomerContacts() {
		return customerContacts;
	}
	
	public static Response setCustomerContacts(ArrayList<CustomerContacts> customerContacts) {
		instance.customerContacts = customerContacts;
		return instance;
	}
	
	
}
