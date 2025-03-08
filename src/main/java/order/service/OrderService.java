package order.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.List;
import order.domain.Order;
import order.dto.OrderCreateDto;
import payment.service.PaymentService;

public class OrderService {

    private final PaymentService paymentService;

    public OrderService() {
        this.paymentService = new PaymentService();
    }

    public void order(List<OrderCreateDto> orders, Admin admin, Customer customer) {
        List<Order> orderList = orders.stream()
            .map(OrderCreateDto::to)
            .toList();

        // TODO: 결제를 안할 수도 이는 것이다. 값만 넘겨줘야 할까.
        paymentService.pay(orderList, admin, customer);
    }

}