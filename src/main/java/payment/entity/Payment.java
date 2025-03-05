package payment.entity;

public class Payment {

    private final String name;
    private final long price;
    private final long quantity;

    public Payment(String menuName, long menuPrice, long orderQuantity) {
        this.name = menuName;
        this.price = menuPrice;
        this.quantity = orderQuantity;
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