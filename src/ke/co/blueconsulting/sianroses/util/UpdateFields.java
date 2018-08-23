package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.salesforce.*;

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
	
	public static ArrayList<ProductChild> updateProductChildSyncFields(ArrayList<ProductChild> products, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<ProductChild> updatedProducts = new ArrayList<>();
		for (ProductChild product : products) {
			product.setPushToSapC(pushToSAP);
			product.setPullFromSapC(pullFromSAP);
			updatedProducts.add(product);
		}
		return updatedProducts;
	}
	
	public static ArrayList<PriceList> updatePriceListSyncFields(ArrayList<PriceList> products, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<PriceList> updatedProducts = new ArrayList<>();
		for (PriceList product : products) {
			product.setPushToSAP(pushToSAP);
			product.setPullFromSAPC(pullFromSAP);
			updatedProducts.add(product);
		}
		return updatedProducts;
	}
	
	public static ArrayList<Warehouse> updateWarehouseSyncFields(ArrayList<Warehouse> warehouses, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<Warehouse> updatedProducts = new ArrayList<>();
		for (Warehouse product : warehouses) {
			product.setPushToSAPC(pushToSAP);
			product.setPullFromSAPC(pullFromSAP);
			updatedProducts.add(product);
		}
		return updatedProducts;
	}
}
