package order.domain;

import menu.domain.Menu;

public class OrderItem {

    private final Menu menu;
    private final long quantity;

    private OrderItem(Menu menu, long quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static OrderItem createOrderItem(String name, long quantity) {
        Menu orededMenu = Menu.findByMenu(name);
        Menu valiedMenu = orededMenu.validateOrderedMenu(quantity);
        return new OrderItem(valiedMenu, quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public String getMenuName() {
        return menu.getName();
    }

    public long getPrice() {
        return menu.getPrice();
    }

    public long getQuantity() {
        return quantity;
    }


}