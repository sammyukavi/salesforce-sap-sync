package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.model.salesforce.Warehouse;

import java.io.Serializable;
import java.util.List;

/**
 * A conversion class used to send data to Salesforce server and back to the SAP server
 *
 * @param <S> Any class used for wrapping data
 */
public class ServerResponse<S> implements Serializable {
	
	@SerializedName("pricelist")
	private List<PriceList> pricelist;
	
	@SerializedName("warehouses")
	private List<Warehouse> warehouses;
	
	@SerializedName("customers")
	private List<Customer> customers;
	
	@SerializedName("products")
	private List<Product> products;
	
	public List<PriceList> getPricelist() {
		return pricelist;
	}
	
	public void setPricelist(List<PriceList> pricelist) {
		this.pricelist = pricelist;
	}
	
	public List<Warehouse> getWarehouses() {
		return warehouses;
	}
	
	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	
	public void setProducts(List<Product> products) {
		this.products = products;
	}
}