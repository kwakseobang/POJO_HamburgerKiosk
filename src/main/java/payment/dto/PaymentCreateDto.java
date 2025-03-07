package payment.dto;

import payment.domain.Payment;

public record PaymentCreateDto(String menuName,long menuPrice, long orderQuantity) {

    public Payment to() {
        return new Payment(menuName, menuPrice * orderQuantity, orderQuantity );
    }
}