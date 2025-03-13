package receipt.service;

import io.domain.OutPut;
import java.util.List;
import payment.domain.Payment;
import receipt.ReceiptDto;

public class ReceiptService {

    public void displayReceipt(ReceiptDto receiptDto) {

        OutPut.displayReceiptHeader();
        List<Payment> payments = receiptDto.paymentList();
        for (Payment payment : payments) {
            OutPut.displayReceiptBody(payment);
        }
        OutPut.displayReceiptFooter(
            receiptDto.admin(),
            receiptDto.customer(),
            receiptDto.totalQuantity(),
            receiptDto.totalPrice());
    }

}