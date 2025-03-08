package customer.repository;

import customer.domain.Customer;
import java.util.HashMap;
import java.util.Optional;

public class CustomerRepository {

    private static final HashMap<String, Customer> customerInfo = new HashMap<>();

    public void create(Customer customer) {
        customerInfo.put(customer.getId(), customer);
    }

    public boolean isExistId(String customerId) {
        return customerInfo.containsKey(customerId);
    }

    public Optional<Customer> findByCustomer(String customerId) {
        return Optional.ofNullable(customerInfo.get(customerId));
    }

}