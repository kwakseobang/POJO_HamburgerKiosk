package order.dto;

import order.domain.OrderItem;

public record OrderItemDto(String menuName, long quantity) {

    public OrderItem to() {
        return OrderItem.createOrderItem(menuName, quantity);
    }

}