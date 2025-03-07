package payment.service;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;
import menu.domain.Menu;
import menu.domain.Set;
import order.domain.Order;
import parser.Parser;
import payment.domain.Payment;
import receipt.service.ReceiptService;

public class PaymentService {

    private final ReceiptService receiptService;

    public PaymentService() {
        this.receiptService = new ReceiptService();
    }

    public void pay(
        List<Order> orders,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = createPaymentList(orders, admin,
            customer);
        long totalPrice = calculateTotalPrice(paymentList);
        long totalQuantity = calculateTotalQuantity(paymentList);
        receiptService.displayReceipt(paymentList, admin, customer, totalQuantity, totalPrice);
    }

    private List<Payment> createPaymentList(
        List<Order> orders,
        Admin admin,
        Customer customer
    ) {
        List<Payment> paymentList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Menu menu = order.getMenu();

            Payment payment = Payment.createPayment(
                menu.getName(),
                menu.getPrice(),
                order.getQuantity()
            );

            updateAmount(admin, customer, payment);
            updateMenuQuantity(menu, payment.getQuantity());
            paymentList.add(payment);
        }
        return paymentList;
    }

    private void updateAmount(
        Admin admin,
        Customer customer,
        Payment payment
    ) {
        customer.updateAmount(payment.getPrice());
        admin.updateAmount(payment.getPrice());
    }

    private void updateMenuQuantity(Menu menu, long quantity) {
        String category = menu.getCategory();
        if (category.equals(Category.SET.getName()) ||
            category.equals(Category.HAMBURGER.getName())
        ) {
            updateSetOrBurger(menu.getName(), quantity, category);
            return;
        }
        menu.updateQuantity(quantity);
    }

    private long calculateTotalPrice(List<Payment> payments) {
        return payments.stream().mapToLong(Payment::getPrice).sum();
    }

    private long calculateTotalQuantity(List<Payment> payments) {
        return payments.stream().mapToLong(Payment::getQuantity).sum();
    }

    private void updateSetOrBurger(String menuName, long quantity, String category) {
        if (category.equals(Category.SET.getName())) {
            updateSide(quantity);
        }
        updateBurger(menuName, quantity);
    }

    private void updateBurger(String menuName, Long quantity) {
        // 햄버거세트일 경우 버거 이름만 추출 후 반환. 햄버거일 경우 그대로 반환
        String burgerName = Parser.parseToBurgerName(menuName);
        Menu burger = Menu.findByMenu(burgerName);
        Menu burgerSet = Menu.findByMenu(burgerName + Category.SET.getName());
        burger.updateQuantity(quantity);
        burgerSet.updateQuantity(quantity);
    }

    private void updateSide(long quantity) {
        Menu potatoMenu = Menu.findByMenu(Set.POTATO.getName());
        Menu drinkMenu = Menu.findByMenu(Set.DRINK.getName());
        potatoMenu.updateQuantity(quantity);
        drinkMenu.updateQuantity(quantity);
    }

}