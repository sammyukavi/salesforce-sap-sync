
package ke.co.blueconsulting.sianroses.model.salesforce;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Result {

    @Expose
    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public static class Builder {

        private List<Customer> customers;

        public Result.Builder withCustomers(List<Customer> customers) {
            this.customers = customers;
            return this;
        }

        public Result build() {
            Result result = new Result();
            result.customers = customers;
            return result;
        }

    }

}
