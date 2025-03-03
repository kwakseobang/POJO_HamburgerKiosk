package customer;

import java.util.HashMap;

public class CustomerRepository {

    private static final HashMap<Long, Customer> customerInfo = new HashMap<>();

    public void create(Customer customer) {
        customerInfo.put(customer.getCustomerId(), customer);
    }

    public boolean isExistId(Long customerId) {
        return customerInfo.containsKey(customerId);
    }

    public Customer findByCustomer(Long customerId) {
        return customerInfo.get(customerId);
    }

}