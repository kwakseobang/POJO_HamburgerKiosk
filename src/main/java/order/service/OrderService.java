package order.service;

import java.util.ArrayList;
import java.util.List;
import menu.entity.Menu;
import menu.response.MenuErrorMessage;
import order.entity.Order;

public class OrderService {

    private static final String SOLD_OUT = "품절";

    public void order(List<Order> orders, List<Menu> menuList) {
        List<Menu> orderByMenu = new ArrayList<>();
        for (Order order : orders) {
            orderByMenu.add(validateOrder(order,menuList));
        }
    }

    private Menu validateOrder(Order order, List<Menu> menuList) {
        Menu menu = findByMenu(order.getName(), menuList);
        validateQuantity(menu.getName(),menu.getQuantity(),order.getQuantity());
        return menu;
    }
    private Menu findByMenu(String name, List<Menu> menuList) {
        return menuList.stream()
            .filter(menu -> menu.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.NOT_EXIST_MENU.getMessage(), name)
            ));
    }

    private void validateQuantity(String name, String menuQuantity, long orderQuantity ) {
        if(menuQuantity.equals(SOLD_OUT)) {
            throw new RuntimeException(String.format(MenuErrorMessage.INVALID_BUY.getMessage(),name));
        }
        if (Long.parseLong(menuQuantity) - orderQuantity < 0) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
    }

}