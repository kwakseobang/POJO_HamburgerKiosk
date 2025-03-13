package order.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.List;
import order.domain.OrderItem;
import order.dto.OrderDto;
import order.dto.OrderItemDto;
import payment.service.PaymentService;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void order(List<OrderItemDto> orderItems, Admin admin, Customer customer) {
        List<OrderItem> orderList = orderItems.stream()
            .map(OrderItemDto::to)
            .toList();

        // TODO: 결제를 안할 수도 이는 것이다. 값만 넘겨줘야 할까.
        paymentService.pay(new OrderDto(orderList,admin, customer));
    }

}