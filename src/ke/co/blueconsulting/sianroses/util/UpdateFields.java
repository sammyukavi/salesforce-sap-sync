package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.BaseSalesforceModel;
import ke.co.blueconsulting.sianroses.model.salesforce.*;

import java.util.ArrayList;

public class UpdateFields {
	
	public static ArrayList<Customer> updateCustomerSyncFields(ArrayList<Customer> customers, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<Customer> updatedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			customer.setPushToSap(pushToSAP);
			customer.setPullFromSap(pullFromSAP);
			updatedCustomers.add(customer);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<CustomerContact> updateCustomerContactSyncFields(ArrayList<CustomerContact> customerContacts, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<CustomerContact> updatedCustomers = new ArrayList<>();
		for (CustomerContact customerContact : customerContacts) {
			customerContact.setPushToSAP(pushToSAP);
			customerContact.setPullFromSAP(pullFromSAP);
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
			product.setPushToSap(pushToSAP);
			product.setPullFromSap(pullFromSAP);
			updatedProducts.add(product);
		}
		return updatedProducts;
	}
	
	public static ArrayList<PriceList> updatePriceListSyncFields(ArrayList<PriceList> products, boolean pushToSAP, boolean pullFromSAP) {
		ArrayList<PriceList> updatedProducts = new ArrayList<>();
		for (PriceList priceList : products) {
			priceList.setPushToSap(pushToSAP);
			priceList.setPullFromSap(pullFromSAP);
			updatedProducts.add(priceList);
		}
		return updatedProducts;
	}
	
	public static ArrayList<? extends BaseSalesforceModel> updateSyncFields(ArrayList<? extends BaseSalesforceModel> models, boolean pushToSAP, boolean pullFromSAP){
		ArrayList<BaseSalesforceModel> updatedModels = new ArrayList<>();
		for (BaseSalesforceModel model : models) {
			model.setPushToSap(pushToSAP);
			model.setPullFromSap(pullFromSAP);
			updatedModels.add(model);
		}
		return updatedModels;
	}
}
