package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;

import java.io.Serializable;
import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.CONTACTS;

public class PushCustomerContacts implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@SerializedName(CONTACTS)
	@Expose
	private ArrayList<CustomerContacts> contacts;
	
	public PushCustomerContacts(ArrayList<CustomerContacts> contacts) {
		this.contacts = contacts;
	}
	
	public ArrayList<CustomerContacts> getContacts() {
		return contacts;
	}
}
