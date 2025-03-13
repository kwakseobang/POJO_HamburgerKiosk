package order.service;

import java.util.List;
import order.domain.OrderItem;
import order.dto.OrderDto;
import order.dto.OrderItemDto;

public class OrderService {

    public OrderDto order(List<OrderItemDto> orderItems) {
        List<OrderItem> orderList = orderItems.stream()
            .map(OrderItemDto::to)
            .toList();
        return new OrderDto(orderList);
    }
}