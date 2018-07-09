package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.*;

import java.io.Serializable;
import java.util.List;

/**
 * A conversion class used to send data to Salesforce server and back to the SAP server
 */
public class ServerResponse implements Serializable {
	
	@SerializedName("customers")
	@Expose
	private List<Customer> customers;
	
	@SerializedName("customer_contacts")
	@Expose
	private List<CustomerContacts> customerContacts;
	
	@SerializedName("price_list")
	@Expose
	private List<PriceList> priceList;
	
	@SerializedName("products")
	@Expose
	private List<Product> products;
	
	@SerializedName("products_children")
	@Expose
	private List<ProductChild> productsChildren;
	
	@SerializedName("warehouses")
	@Expose
	private List<Warehouse> warehouses;
	
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<CustomerContacts> getCustomerContacts() {
		return customerContacts;
	}
	
	public void setCustomerContacts(List<CustomerContacts> customerContacts) {
		this.customerContacts = customerContacts;
	}
	
	public List<PriceList> getPriceList() {
		return priceList;
	}
	
	public void setPriceList(List<PriceList> priceList) {
		this.priceList = priceList;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public List<ProductChild> getProductsChildren() {
		return productsChildren;
	}
	
	public void setProductsChildren(List<ProductChild> productsChildren) {
		this.productsChildren = productsChildren;
	}
}