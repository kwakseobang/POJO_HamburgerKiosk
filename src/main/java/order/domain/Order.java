package order.domain;

import java.util.List;

public class Order {

    private long totalPrice;
    private long totalQuantity;
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
        calculateTotalPrice();
        calculateTotalQuantity();
    }

    public static Order createOrder(List<OrderItem> orderItems) {
       return new Order(orderItems);
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public List<OrderItem> getOrderItem() {
        return orderItems;
    }

    private void calculateTotalPrice() {
        this.totalPrice = orderItems
            .stream()
            .mapToLong(item -> item.getPrice() * item.getQuantity())
            .sum();
    }

    private void calculateTotalQuantity() {
        this.totalQuantity = orderItems
            .stream()
            .mapToLong(OrderItem::getQuantity)
            .sum();
    }

}