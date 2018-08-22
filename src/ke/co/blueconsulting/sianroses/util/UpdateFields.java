package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContact;
import ke.co.blueconsulting.sianroses.model.salesforce.Product;

import java.util.ArrayList;

public class UpdateFields {
	
	public static ArrayList<Customer> updateCustomerSyncFields(ArrayList<Customer> customers, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<Customer> updatedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			customer.setPushToSAPC(pushToSAP);
			customer.setPullFromSAPC(pullFromSAP);
			updatedCustomers.add(customer);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<CustomerContact> updateCustomerContactSyncFields(ArrayList<CustomerContact> customerContacts, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<CustomerContact> updatedCustomers = new ArrayList<>();
		for (CustomerContact customerContact : customerContacts) {
			customerContact.setPushToSap(pushToSAP);
			customerContact.setPullFromSap(pullFromSAP);
			updatedCustomers.add(customerContact);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<Product> updateProductSyncFields(ArrayList<Product> products, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<Product> updatedProducts = new ArrayList<>();
		for (Product product : products) {
			product.setPushToSAPC(pushToSAP);
			product.setPullFromSAPC(pullFromSAP);
			updatedProducts.add(product);
		}
		return updatedProducts;
	}
}
