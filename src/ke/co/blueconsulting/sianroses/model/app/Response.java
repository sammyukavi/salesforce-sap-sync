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
	
	/**
	 *
	 */
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
	
	@SerializedName("products")
	@Expose
	private ArrayList<Product> products = new ArrayList<>();
	
	@SerializedName("products_children")
	@Expose
	private ArrayList<ProductChild> productsChildren = new ArrayList<>();
	
	@SerializedName("warehouses")
	@Expose
	private ArrayList<Warehouse> warehouses = new ArrayList<>();
	
	public ArrayList<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers.clear();
		this.customers.addAll(customers);
	}
	
	public ArrayList<CustomerContacts> getCustomerContacts() {
		return customerContacts;
	}
	
	public void setCustomerContacts(ArrayList<CustomerContacts> customerContacts) {
		this.customerContacts.clear();
		this.customerContacts.addAll(customerContacts);
	}
	
	public ArrayList<PriceList> getPriceList() {
		return priceList;
	}
	
	public void setPriceList(ArrayList<PriceList> priceList) {
		this.priceList.clear();
		this.priceList.addAll(priceList);
	}
	
	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public void setProducts(ArrayList<Product> products) {
		this.products.clear();
		this.products.addAll(products);
	}
	
	public ArrayList<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(ArrayList<Warehouse> warehouses) {
		this.warehouses.clear();
		this.warehouses.addAll(warehouses);
	}
	
	public ArrayList<ProductChild> getProductsChildren() {
		return productsChildren;
	}
	
	public void setProductsChildren(ArrayList<ProductChild> productsChildren) {
		this.productsChildren.clear();
		this.productsChildren.addAll(productsChildren);
	}
}
