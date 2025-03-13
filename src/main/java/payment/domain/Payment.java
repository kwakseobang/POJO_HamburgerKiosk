package payment.domain;

import admin.domain.Admin;
import customer.domain.Customer;
import order.domain.OrderItem;

public class Payment {

    private final String name;
    private final long price;
    private final long quantity;

    private Payment(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // 생성 시 유효성 검사, 변환, 추가 로직 등 다양하게 사용 가능
    public static Payment createPayment(OrderItem orderItem) {
        String menuName = orderItem.getMenuName();
        long payPrice = orderItem.getPrice() * orderItem.getQuantity();
        long menuQuantity = orderItem.getQuantity();
        return new Payment(menuName, payPrice, menuQuantity);
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

    public void updateAmount(Admin admin, Customer customer) {
        customer.updateAmount(this.price);
        admin.updateAmount(this.price);
    }

}