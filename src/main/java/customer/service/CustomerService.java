package customer.service;

import admin.domain.Admin;
import customer.domain.Customer;
import customer.repository.CustomerRepository;
import customer.response.CustomerErrorMessage;
import io.domain.Input;
import io.domain.OutPut;
import menu.domain.MenuList;
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

    /* TODO:
        메뉴리스트는 출력 용으로 밖에 쓰이는데 출력을 차리라 메뉴리스트를 넘기지말고 호출하는 쪽으로 옮길까
        고민 중.. 키오스크가 주문객체에게 전달하는건다. 그럼 customer 서비스에서 하는 게 주문 요청도 안하고 생성 로그인만 하는데..
    */
    public void order(MenuList menuList, Admin admin, Customer customer) {
        OutPut.displayMenuList(menuList.getMenuList());
        orderService.order(Input.inputMenu(), admin, customer);
    }

}