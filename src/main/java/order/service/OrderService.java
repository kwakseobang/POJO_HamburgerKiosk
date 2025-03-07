package order.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;
import order.domain.Order;
import payment.service.PaymentService;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void order(List<Order> orders, List<Menu> menuList, Admin admin, Customer customer) {
//       유효성 검증 된 주문이 들어온다.
        List<Menu> orderedMenuList = new ArrayList<>();
        for (Order order : orders) {
            orderedMenuList.add(order.validateOrderedMenu(menuList));
        }
        paymentService.pay(orders, orderedMenuList, admin, customer);
    }

}