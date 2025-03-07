package receipt.service;

import admin.domain.Admin;
import customer.domain.Customer;
import io.domain.OutPut;
import java.util.List;
import payment.domain.Payment;

public class ReceiptService {

    public void displayReceipt(
        List<Payment> paymentList,
        Admin admin,
        Customer customer,
        long totalQuantity,
        long totalPrice

    ) {
        OutPut.displayReceiptHeader();
        for (Payment payment : paymentList) {
            OutPut.displayReceiptBody(payment);
        }
        OutPut.displayReceiptFooter(admin, customer, totalQuantity, totalPrice);
    }

}