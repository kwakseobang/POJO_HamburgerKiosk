package order.dto;

import java.util.List;
import order.domain.OrderItem;

public record OrderDto(List<OrderItem> orderItems) {
}