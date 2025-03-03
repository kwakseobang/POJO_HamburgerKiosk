package customer.service;

import admin.entity.Admin;
import admin.response.AdminErrorMessage;
import customer.response.CustomerErrorMessage;
import customer.repository.CustomerRepository;
import customer.entity.Customer;
import io.Input;
import order.OrderService;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public CustomerService() {
        this.orderService = new OrderService();
        this.customerRepository = new CustomerRepository();
    }

    public void createCustomer() {
        Customer customer = Input.inputCustomerInfo();
        // TODO: 이 로직을 도메인에 넣어야 할 수도 있습니다.
        if (customerRepository.isExistId(customer.getCustomerId())) {
            throw new IllegalArgumentException(
                String.format(CustomerErrorMessage.DUPLICATION_NAME.getMessage(),
                    customer.getCustomerId()));
        }
        customerRepository.create(customer);
    }

    public Customer login() {
        long id = Input.inputUniqueNumber();
        Customer customer = customerRepository.findByCustomer(id);
        if (customer != null) {
            return customer;
        }
        throw new IllegalArgumentException(
            String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), id));
    }

    public void order(Customer customer, Admin admin) {
        orderService.buy(customer, admin);
    }


    public void updateAmount(Admin admin, long amount) {
        admin.updateAmount(amount);
    }
}