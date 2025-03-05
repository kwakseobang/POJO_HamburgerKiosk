package payment.service;

import admin.entity.Admin;
import customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.entity.Menu;
import order.entity.Order;
import payment.dto.PaymentCreateDto;
import payment.entity.Payment;
import receipt.service.ReceiptService;

public class PaymentService {

    private final ReceiptService receiptService;

    public PaymentService() {
        this.receiptService = new ReceiptService();
    }

    public void pay(List<Order> orders, List<Menu> orderByMenu, Admin admin, Customer customer) {
        List<Payment> paymentList = createPayment(orders, orderByMenu, admin, customer);
        long totalPrice = calculateTotalPrice(paymentList);
        long totalQuantity = calculateTotalQuantity(paymentList);
        receiptService.displayReceipt(customer, admin, paymentList, totalPrice, totalQuantity);
    }

    private List<Payment> createPayment(
        List<Order> orders,
        List<Menu> orderByMenu,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < orderByMenu.size(); i++) {
            Menu menu = orderByMenu.get(i);
            Order order = orders.get(i);

            PaymentCreateDto paymentCreateDto = new PaymentCreateDto(
                menu.getName(),
                menu.getPrice(),
                order.getQuantity()
            );
            Payment payment = paymentCreateDto.to();
            update(menu, admin, customer, payment);
            paymentList.add(payment);
        }
        return paymentList;
    }

    private void update(Menu menu, Admin admin, Customer customer, Payment payment) {
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

}