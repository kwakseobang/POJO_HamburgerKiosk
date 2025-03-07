package order.dto;

import order.domain.Order;

public record OrderCreateDto(String menuName, long quantity) {

    public Order to() {
        return Order.createOrder(menuName, quantity);
    }

}