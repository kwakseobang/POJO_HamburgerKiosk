package customer.repository;

import customer.entity.Customer;
import java.util.HashMap;
import java.util.Optional;

public class CustomerRepository {

    private static final HashMap<Long, Customer> customerInfo = new HashMap<>();

    public void create(Customer customer) {
        customerInfo.put(customer.getCustomerId(), customer);
    }

    public boolean isExistId(Long customerId) {
        return customerInfo.containsKey(customerId);
    }

    public Optional<Customer> findByCustomer(Long customerId) {
        return Optional.ofNullable(customerInfo.get(customerId));
    }

}