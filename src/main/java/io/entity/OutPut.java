package io.entity;

import io.response.OutPutMessage;
import java.util.List;
import menu.entity.Menu;

public class OutPut {

    private OutPut() {
    }

    public static void displayMenuList(List<Menu> menuList) {
        for (Menu menu : menuList) {
            System.out.printf(OutPutMessage.DISPLAY_MENU.getMessage(),
                menu.getName(),
                menu.getPrice() + "원",
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

    private static String isSoldOut(String menuQuantity) {
        if (menuQuantity.equals("품절")) {
            return menuQuantity;
        }
        return menuQuantity + "개";
    }

}