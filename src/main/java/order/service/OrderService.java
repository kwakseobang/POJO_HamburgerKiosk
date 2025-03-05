package order.service;

import admin.entity.Admin;
import customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.entity.Menu;
import menu.response.MenuErrorMessage;
import order.entity.Order;
import payment.service.PaymentService;

public class OrderService {

    private static final int MIN_QUANTITY = 0;
    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void order(List<Order> orders, List<Menu> menuList, Admin admin, Customer customer) {
        List<Menu> orderByMenu = new ArrayList<>();
        for (Order order : orders) {
            orderByMenu.add(validateOrder(order, menuList));
        }
        paymentService.pay(orders, orderByMenu,admin, customer);
    }

    private Menu validateOrder(Order order, List<Menu> menuList) {
        Menu menu = findByMenu(order.getName(), menuList);
        validateQuantity(menu, order.getQuantity());
        return menu;
    }

    private Menu findByMenu(String name, List<Menu> menuList) {
        return menuList.stream()
            .filter(menu -> menu.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.NOT_EXIST_MENU.getMessage(), name)
            ));
    }

    private void validateQuantity(Menu menu, long orderQuantity) {
        if (menu.isSoldOut()) {
            throw new RuntimeException(
                String.format(MenuErrorMessage.INVALID_BUY.getMessage(), menu.getName()));
        }
        if (menu.calculateQuantity(orderQuantity) < MIN_QUANTITY) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
    }

}