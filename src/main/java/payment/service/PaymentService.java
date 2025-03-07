package payment.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Menu;
import order.domain.Order;
import payment.dto.PaymentCreateDto;
import payment.domain.Payment;
import receipt.service.ReceiptService;

public class PaymentService {

    private final ReceiptService receiptService;

    public PaymentService() {
        this.receiptService = new ReceiptService();
    }

    public void pay(
        List<Order> orders,
        List<Menu> orderByMenu,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = createPaymentList(orders, orderByMenu, admin, customer);
        long totalPrice = calculateTotalPrice(paymentList);
        long totalQuantity = calculateTotalQuantity(paymentList);
        receiptService.displayReceipt(customer, admin, paymentList, totalPrice, totalQuantity);
    }

    private List<Payment> createPaymentList(
        List<Order> orders,
        List<Menu> orderByMenu,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < orderByMenu.size(); i++) {
            Menu menu = orderByMenu.get(i);
            Order order = orders.get(i);

            Payment payment = createPayment(menu.getName(), menu.getPrice(), order.getQuantity());
            update(menu, admin, customer, payment);
            paymentList.add(payment);
        }
        return paymentList;
    }

    private void update(
        Menu menu,
        Admin admin,
        Customer customer,
        Payment payment
    ) {
        customer.updateAmount(payment.getPrice());
        admin.updateAmount(payment.getPrice());
        menu.updateQuantity(payment.getQuantity());
    }

    private long calculateTotalPrice(List<Payment> payments) {
        return payments.stream().mapToLong(Payment::getPrice).sum();
    }

    private long calculateTotalQuantity(List<Payment> payments) {
        return payments.stream().mapToLong(Payment::getQuantity).sum();
    }

    private Payment createPayment(String menuName, long menuPrice, long orderQuantity) {
        PaymentCreateDto paymentCreateDto = new PaymentCreateDto(
            menuName,
            menuPrice,
            orderQuantity
        );
        return paymentCreateDto.to();
    }

}