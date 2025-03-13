package payment.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;
import order.domain.Order;
import order.domain.OrderItem;
import order.dto.OrderDto;
import payment.domain.Payment;
import receipt.ReceiptDto;
import receipt.service.ReceiptService;

public class PaymentService {

    private final ReceiptService receiptService;

    public PaymentService() {
        this.receiptService = new ReceiptService();
    }

    public void pay(OrderDto orderDto) {
        Order order = orderDto.to();
        Admin admin = orderDto.admin();
        Customer customer = orderDto.customer();
        List<Payment> paymentList = createPaymentList(order, admin,
            customer);
        long totalPrice = order.getTotalPrice();
        long totalQuantity = order.getTotalQuantity();
        receiptService.displayReceipt(new ReceiptDto(paymentList, admin, customer, totalQuantity, totalPrice));
    }

    private List<Payment> createPaymentList(
        Order order,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItem();

        for(OrderItem orderItem: orderItems) {
            Payment payment = Payment.createPayment(orderItem);
            Menu menu = orderItem.getMenu();
            payment.updateAmount(admin, customer);
            menu.updateMenuQuantity(payment.getQuantity());
            paymentList.add(payment);
        }
        return paymentList;
    }

}