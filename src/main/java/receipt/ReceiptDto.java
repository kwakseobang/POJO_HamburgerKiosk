package receipt;

import java.util.List;
import payment.domain.Payment;

public record ReceiptDto(
    List<Payment> paymentList,
    long totalQuantity,
    long totalPrice
) {

}