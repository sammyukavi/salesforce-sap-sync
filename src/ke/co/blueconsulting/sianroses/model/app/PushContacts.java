package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypes.CONTACTS;

public class PushContacts {

    @SerializedName(CONTACTS)
    @Expose
    private ArrayList<CustomerContacts> contacts;

    public PushContacts(ArrayList<CustomerContacts> contacts) {
        this.contacts = contacts;
    }
}
