package io.domain;

import io.response.OutPutMessage;
import java.util.List;
import menu.domain.Menu;
import menu.domain.MenuStatus;
import payment.domain.Payment;
import user.User;

public class OutPut {

    private OutPut() {
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void displayMenuList(List<Menu> menuList) {
        for (Menu menu : menuList) {
            System.out.printf(OutPutMessage.DISPLAY_MENU.getMessage(),
                menu.getName(),
                menu.getPrice() + MenuStatus.WON.getName(),
                isSoldOut(menu.getQuantity()),
                menu.getDescription(),
                menu.getCategory()
            );
        }
    }

    public static void displayIntro(String customerId, String adminName) {
        System.out.printf(
            (OutPutMessage.DISPLAY_INTRO.getMessage()) + "%n",
            customerId, adminName
        );
    }

    public static void displayReceiptHeader() {
        System.out.println(OutPutMessage.DISPLAY_RECEIPT_HEADER.getMessage());
    }

    public static void displayReceiptBody(Payment payment) {
        System.out.printf(
            OutPutMessage.DISPLAY_RECEIPT_BODY.getMessage(),
            payment.getName(),
            payment.getQuantity(),
            payment.getPrice()
        );
    }

    public static void displayReceiptFooter(
        User admin,
        User customer,
        long totalQuantity,
        long totalPrice
    ) {
        System.out.printf(
            OutPutMessage.DISPLAY_RECEIPT_FOOTER.getMessage(),
            totalQuantity,
            totalPrice,
            admin.getId(),
            admin.getAmount(),
            customer.getId(),
            customer.getAmount()
        );
    }

    private static String isSoldOut(Long menuQuantity) {
        if (menuQuantity == 0) {
            return MenuStatus.SOLD_OUT.getName();
        }
        return menuQuantity + MenuStatus.COUNT.getName();
    }

}