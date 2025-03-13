package order.dto;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.List;
import order.domain.OrderItem;

public record OrderDto(List<OrderItem> orderItems, Admin admin, Customer customer) {
}