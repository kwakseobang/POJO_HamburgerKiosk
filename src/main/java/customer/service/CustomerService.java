package customer.service;

import admin.domain.Admin;
import customer.domain.Customer;
import customer.repository.CustomerRepository;
import customer.response.CustomerErrorMessage;
import io.domain.Input;
import order.service.OrderService;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.orderService = new OrderService();
    }

    public void create(Customer customer) {
        if (customerRepository.isExistId(customer.getId())) {
            throw new IllegalArgumentException(
                String.format(CustomerErrorMessage.DUPLICATION_CUSTOMER.getMessage(),
                    customer.getId()));
        }
        customerRepository.create(customer);
    }

    public Customer login(String id) {
        return customerRepository.findByCustomer(id)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(CustomerErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), id)
            ));
    }

    public void order(Admin admin, Customer customer) {
        orderService.order(Input.inputMenu(), admin, customer);
    }

}