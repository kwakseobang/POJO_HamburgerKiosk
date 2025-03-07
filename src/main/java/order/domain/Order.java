package order.domain;

import menu.domain.Menu;

public class Order {

    private Menu menu;
    private long quantity;

    private Order(Menu menu, long quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public static Order createOrder(String name, long quantity) {
        Menu orededMenu = Menu.findByMenu(name);
        Menu valiedMenu = orededMenu.validateOrderedMenu(quantity);
        return new Order(valiedMenu, quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public long getQuantity() {
        return quantity;
    }

}