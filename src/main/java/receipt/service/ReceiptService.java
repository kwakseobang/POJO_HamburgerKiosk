package receipt.service;

import io.domain.OutPut;
import java.util.List;
import payment.domain.Payment;
import receipt.ReceiptDto;
import user.User;

public class ReceiptService {

    public void displayReceipt(ReceiptDto receiptDto, User admin, User customer) {

        OutPut.displayReceiptHeader();
        List<Payment> payments = receiptDto.paymentList();
        for (Payment payment : payments) {
            OutPut.displayReceiptBody(payment);
        }
        OutPut.displayReceiptFooter(
            admin,
            customer,
            receiptDto.totalQuantity(),
            receiptDto.totalPrice());
    }

}