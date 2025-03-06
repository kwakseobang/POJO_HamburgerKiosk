package order.entity;

import java.util.List;
import menu.entity.Menu;
import menu.response.MenuErrorMessage;

public class Order {

    private String name;
    private long quantity;

    public Order(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public Menu validateOrderedMenu(List<Menu> menuList) {
            Menu menu = findByMenu(this.name, menuList);
            validateQuantity(menu);
            return menu;
    }

    private Menu findByMenu(String name, List<Menu> menuList) {
        return menuList.stream()
            .filter(menu -> menu.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.NOT_EXIST_MENU.getMessage(), name)
            ));
    }

    private void validateQuantity(Menu menu) {
        int minQuantity = 0;
        if (menu.isSoldOut()) {
            throw new IllegalArgumentException(
                String.format(MenuErrorMessage.INVALID_BUY.getMessage(), menu.getName()));
        }
        if (menu.calculateQuantity(this.quantity) < minQuantity ) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
    }

}