package customer;

import admin.Admin;
import admin.AdminErrorMessage;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        // TODO: 이 로직을 도메인에 넣어야 할 수도 있습니다.
        if (customerRepository.isExistId(customer.getCustomerId())) {
            throw new IllegalArgumentException(
                String.format(CustomerErrorMessage.DUPLICATION_NAME.getMessage(), customer.getCustomerId()));
        }
        customerRepository.create(customer);
        return customer;
    }

    public Customer login(long id) {
        Customer customer = customerRepository.findByCustomer(id);
        if (customer != null) {
            return customer;
        }
        throw new IllegalArgumentException(
            String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), id));
    }

    public void updateAmount(Admin admin, long amount) {
        admin.updateAmount(amount);
    }
}