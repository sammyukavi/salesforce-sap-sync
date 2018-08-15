package ke.co.blueconsulting.sianroses.util;

import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;

import java.util.ArrayList;

public class UpdateFields {
	
	public static ArrayList<Customer> updateCustomerPushToSAPFields(ArrayList<Customer> customers) {
		ArrayList<Customer> updatedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			customer.setPushToSAPC(false);
			updatedCustomers.add(customer);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<Customer> updateCustomerPullFromSAPFields(ArrayList<Customer> customers) {
		ArrayList<Customer> updatedCustomers = new ArrayList<>();
		for (Customer customer : customers) {
			customer.setPullFromSAPC(false);
			updatedCustomers.add(customer);
		}
		return updatedCustomers;
	}
	
	public static ArrayList<CustomerContacts> updateCustomerContactsPushToSAPFields(ArrayList<CustomerContacts> customers) {
		ArrayList<CustomerContacts> updatedCustomers = new ArrayList<>();
		for (CustomerContacts customer : customers) {
			customer.setPushToSap(false);
			updatedCustomers.add(customer);
		}
		return updatedCustomers;
	}
}
