package payment.entity;

public class Payment {

    private String name;
    private long price;
    private long quantity;

    public Payment(String menuName,long menuPrice, long orderQuantity) {
        this.name = menuName;
        this.price = menuPrice * orderQuantity;
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