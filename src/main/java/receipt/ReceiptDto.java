package receipt;

import admin.domain.Admin;
import customer.domain.Customer;
import java.util.List;
import payment.domain.Payment;

public record ReceiptDto(
    List<Payment> paymentList,
    Admin admin,
    Customer customer,
    long totalQuantity,
    long totalPrice
) {

}