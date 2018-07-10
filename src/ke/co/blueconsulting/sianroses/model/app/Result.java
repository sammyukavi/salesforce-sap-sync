package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A conversion class used to send data to Salesforce server and back to the SAP server
 */
public class Result implements Serializable {
	
	@SerializedName("customers")
	@Expose
	private List<Customer> customers = new ArrayList<>();
	
	@SerializedName("customer_contacts")
	@Expose
	private List<CustomerContacts> customerContacts = new ArrayList<>();
	
	@SerializedName("price_list")
	@Expose
	private List<PriceList> priceList = new ArrayList<>();
	
	@SerializedName("products")
	@Expose
	private List<Product> products = new ArrayList<>();
	
	@SerializedName("products_children")
	@Expose
	private List<ProductChild> productsChildren = new ArrayList<>();
	
	@SerializedName("warehouses")
	@Expose
	private List<Warehouse> warehouses = new ArrayList<>();
	
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers.clear();
		this.customers.addAll(customers);
	}
	
	public List<CustomerContacts> getCustomerContacts() {
		return customerContacts;
	}
	
	public void setCustomerContacts(List<CustomerContacts> customerContacts) {
		this.customerContacts.clear();
		this.customerContacts.addAll(customerContacts);
	}
	
	public List<PriceList> getPriceList() {
		return priceList;
	}
	
	public void setPriceList(List<PriceList> priceList) {
		this.priceList.clear();
		this.priceList.addAll(priceList);
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products.clear();
		this.products.addAll(products);
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses.clear();
		this.warehouses.addAll(warehouses);
	}
	
	public List<ProductChild> getProductsChildren() {
		return productsChildren;
	}
	
	public void setProductsChildren(List<ProductChild> productsChildren) {
		this.productsChildren.clear();
		this.productsChildren.addAll(productsChildren);
	}
}