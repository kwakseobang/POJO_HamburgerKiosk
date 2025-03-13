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

        // TODO: 바로 결제 객체에게 메시지를 보내는 게 아닌 주문 정보 반환 후 Kiosk 에서 처리 예정.
        paymentService.pay(new OrderDto(orderList,admin, customer));
    }

}