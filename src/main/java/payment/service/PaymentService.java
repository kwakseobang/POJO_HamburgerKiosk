package payment.service;

import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;
import order.domain.Order;
import order.domain.OrderItem;
import order.dto.OrderDto;
import payment.domain.Payment;
import receipt.ReceiptDto;
import user.User;

public class PaymentService {


    public ReceiptDto pay(OrderDto orderDto, User admin, User customer) {
        Order order = Order.createOrder(orderDto.orderItems());
        List<Payment> paymentList = createPaymentList(order, admin, customer);
        long totalPrice = order.getTotalPrice();
        long totalQuantity = order.getTotalQuantity();

        return new ReceiptDto(paymentList, totalQuantity, totalPrice);
    }

    private List<Payment> createPaymentList(Order order, User admin, User customer) {
        List<Payment> paymentList = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItem();

        for (OrderItem orderItem : orderItems) {
            Payment payment = Payment.createPayment(orderItem);
            Menu menu = orderItem.getMenu();
            payment.updateAmount(admin, customer);
            menu.updateMenuQuantity(payment.getQuantity());
            paymentList.add(payment);
        }
        return paymentList;
    }

}