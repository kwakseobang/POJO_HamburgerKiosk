package customer.service;

import customer.domain.Customer;
import customer.response.CustomerErrorMessage;
import io.domain.Input;
import order.dto.OrderDto;
import order.service.OrderService;
import user.User;
import user.UserRepository;

public class CustomerService {

    private final UserRepository userRepository;
    private final OrderService orderService;

    public CustomerService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.orderService = new OrderService();
    }

    public void create(Customer customer) {
        if (userRepository.isExistById(customer.getId())) {
            throw new IllegalArgumentException(
                String.format(CustomerErrorMessage.DUPLICATION_CUSTOMER.getMessage(),
                    customer.getId()));
        }
        userRepository.create(customer);
    }

    public User login(String id) {
        return userRepository.findByUser(id)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(CustomerErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), id)
            ));
    }

    public OrderDto order() {
        return orderService.order(Input.inputMenu());
    }

}