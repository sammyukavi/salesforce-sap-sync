package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.PriceList;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;
import ke.co.blueconsulting.sianroses.model.salesforce.Warehouse;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A conversion class used to send data to Salesforce server and back to the SAP server
 *
 * @param <S> Any class used for wrapping data
 */
public class ServerResponse<S> implements Serializable {
	
	@SerializedName("data")
	@Expose
	private Map<String, List<S>> data = new HashMap<>();
	
	@SerializedName("pricelist")
	private List<PriceList> pricelist;
	
	@SerializedName("warehouses")
	private List<Warehouse> warehouses;
	
	@SerializedName("customers")
	private List<Customer> customers;
	
	@SerializedName("products")
	private List<Product> products;
	
	public void addData(String key, List<S> value) {
		data.put(key, value);
	}
	
	public Map<String, List<S>> getData() {
		return data;
	}
	
	public void setData(Map<String, List<S>> data) {
		this.data.clear();
		this.data.putAll(data);
	}
}