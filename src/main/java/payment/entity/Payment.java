package payment.entity;

import menu.entity.Menu;
import order.entity.Order;

public class Payment {

    private String name;
    private long quantity;
    private long price;

    public Payment(Order order, Menu menu) {
        this.name = menu.getName();
        this.quantity = order.getQuantity();;
        this.price = menu.getPrice() * quantity;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

}