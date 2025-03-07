package receipt.service;

import admin.domain.Admin;
import customer.domain.Customer;
import io.domain.OutPut;
import java.util.List;
import payment.domain.Payment;

public class ReceiptService {

    public void displayReceipt(
        Customer customer,
        Admin admin,
        List<Payment> paymentList,
        long totalPrice,
        long totalQuantity
    ) {
        OutPut.displayReceiptHeader();
        for (Payment payment : paymentList) {
            OutPut.displayReceiptBody(payment);
        }
        OutPut.displayReceiptFooter(admin, customer, totalQuantity, totalPrice);
    }

}