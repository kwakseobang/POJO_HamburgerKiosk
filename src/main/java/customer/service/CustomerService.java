package customer.service;

import admin.entity.Admin;
import customer.entity.Customer;
import customer.repository.CustomerRepository;
import customer.response.CustomerErrorMessage;
import io.entity.Input;
import io.entity.OutPut;
import java.util.List;
import menu.entity.Menu;
import order.service.OrderService;

public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderService orderService;

    public CustomerService() {
        this.customerRepository = new CustomerRepository();
        this.orderService = new OrderService();
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

    public void order(List<Menu> menuList, Admin admin, Customer customer) {
        OutPut.displayMenuList(menuList);
        orderService.order(Input.inputMenu(), menuList, admin, customer);
    }

}