package io.entity;

import admin.entity.Admin;
import io.response.InputErrorMessage;
import io.response.InputMessage;
import java.util.Scanner;
import parser.Parser;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static Admin inputAdminInfo() {
        String input = input(InputMessage.CREATE_ADMIN.getMessage());
        return Parser.parseToAdminInfo(input);
    }

    public static String inputAdminName() {
        return input(InputMessage.LOGIN_ADMIN.getMessage());
    }

//    public static Customer inputCustomerInfo() {
//        String input = input(
//            InputMessage.CREATE_CUSTOMER.getMessage());
//        return Parser.parseToCustomerInfo(input);
//    }

    public static long inputUniqueNumber() {
        return Long.parseLong(
            input(InputMessage.LOGIN_CUSTOMER.getMessage()));
    }

    public static String inputOption() {
        return input(InputMessage.DISPLAY_OPTION.getMessage());
    }

//    public static List<OrderDto> inputMenu() {
//        String orders = input(InputMessage.ORDER_MENU.getMessage());
//        return Parser.parseToOrders(orders);
//    }

    private static String input(String message) {
        displayMessage(message);
        String input = sc.nextLine();
        validateInput(input);
        return input;
    }

    private static void displayMessage(String message) {
        System.out.println(message);
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                String.format(InputErrorMessage.EMPTY_INPUT.getMessage())
            );
        }
    }

}