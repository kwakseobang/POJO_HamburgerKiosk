package order.entity;

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

    // 메뉴를 주문한다.
    public void buy() {

    }

}