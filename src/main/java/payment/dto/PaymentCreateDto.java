package payment.dto;

import menu.entity.Category;
import menu.entity.Menu;
import order.entity.Order;
import payment.entity.Payment;

public record PaymentCreateDto(String menuName,long menuPrice, long orderQuantity) {

    public Payment to() {
        new Payment(menuName,menuPrice,menuPrice * orderQuantity);
    }
}
