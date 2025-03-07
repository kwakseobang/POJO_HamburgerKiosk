package io.domain;

import admin.domain.Admin;
import customer.domain.Customer;
import io.response.OutPutMessage;
import java.util.List;
import menu.domain.Menu;
import menu.domain.MenuStatus;
import payment.domain.Payment;

public class OutPut {

    private OutPut() {
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

    public static void displayIntro(long customerId, String adminName) {
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
        Admin admin,
        Customer customer,
        long totalPrice,
        long totalQuantity
    ) {
        System.out.printf(
            OutPutMessage.DISPLAY_RECEIPT_FOOTER.getMessage(),
            totalQuantity,
            totalPrice,
            admin.getName(),
            admin.getAmount(),
            customer.getCustomerId(),
            customer.getAmount()
        );
    }

    private static String isSoldOut(String menuQuantity) {
        if (menuQuantity.equals(MenuStatus.SOLD_OUT.getName())) {
            return menuQuantity;
        }
        return menuQuantity + MenuStatus.COUNT.getName();
    }

}