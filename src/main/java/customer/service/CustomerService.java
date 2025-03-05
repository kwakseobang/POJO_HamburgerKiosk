package customer.service;

import customer.entity.Customer;
import customer.repository.CustomerRepository;
import customer.response.CustomerErrorMessage;
import io.entity.Input;

public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public void create(Customer customer) {
        if (customerRepository.isExistId(customer.getCustomerId())) {
            throw new IllegalArgumentException(
                String.format(CustomerErrorMessage.DUPLICATION_CUSTOMER.getMessage(),
                    customer.getCustomerId()));
        }
        customerRepository.create(customer);
    }

    public Customer login(long id) {
        return customerRepository.findByCustomer(id)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(CustomerErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), id)
            ));
    }

    public void order() {

    }

}