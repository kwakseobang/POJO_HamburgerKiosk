package order.service;

import admin.entity.Admin;
import customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.entity.Menu;
import order.entity.Order;
import payment.service.PaymentService;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void order(List<Order> orders, List<Menu> menuList, Admin admin, Customer customer) {
        List<Menu> orderByMenu = new ArrayList<>();
        for (Order order : orders) {
            orderByMenu.add(order.validateOrderedMenu(menuList));
        }
        paymentService.pay(orders, orderByMenu, admin, customer);
    }

}