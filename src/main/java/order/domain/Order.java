package order.domain;

import java.util.List;
import menu.domain.Category;
import menu.domain.Menu;
import menu.domain.Set;
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

    public Menu validateOrderedMenu() {
        Menu menu = Menu.findByMenu(this.name);
        validateQuantity(menu);
        if (menu.getCategory().equals(Category.SET.getName())) {
            validateSet();
        }
        return menu;
    }

    private void validateQuantity(Menu menu) {
        int minQuantity = 0;
        if (menu.isSoldOut()) {
            throw new IllegalArgumentException(
                String.format(MenuErrorMessage.INVALID_BUY.getMessage(), menu.getName()));
        }
        if (menu.calculateQuantity(this.quantity) < minQuantity) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
    }

    public void validateSet() {
        Menu drink = Menu.findByMenu(Set.DRINK.getName());
        Menu potato = Menu.findByMenu(Set.POTATO.getName());
        validateQuantity(drink);
        validateQuantity(potato);
    }

}