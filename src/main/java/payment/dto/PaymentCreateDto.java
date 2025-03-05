package payment.dto;

import payment.entity.Payment;

public record PaymentCreateDto(String menuName,long menuPrice, long orderQuantity) {

    public Payment to() {
        return new Payment(menuName, menuPrice, menuPrice * orderQuantity);
    }
}