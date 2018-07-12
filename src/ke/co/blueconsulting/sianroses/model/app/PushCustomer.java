package ke.co.blueconsulting.sianroses.model.app;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;

import java.util.ArrayList;

import static ke.co.blueconsulting.sianroses.util.Constants.DataTypes.ACCOUNTS;

public class PushCustomer {

    @SerializedName(ACCOUNTS)
    @Expose
    private ArrayList<Customer> accounts;

    public PushCustomer(ArrayList<Customer> accounts) {
        this.accounts = accounts;
    }
}
