package io;

import static util.Parser.parseToOrders;

import admin.entity.Admin;
import admin.response.AdminErrorMessage;
import customer.entity.Customer;
import io.response.InputMessage;
import java.util.List;
import java.util.Scanner;
import order.OrderDto;
import util.Parser;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static Admin inputAdminInfo() {
        String input = input(InputMessage.CREATE_ADMIN.getMessage());  // ex) 생성하실 관리자의 이름과 보유금액을 입력해주세요.
        return Parser.parseToAdminInfo(input);
    }

    public static String inputAdminName() {
        return input(InputMessage.LOGIN_ADMIN.getMessage()); // ex) 접속하시는 관리자의 이름을 입력해주세요.
    }

    public static Customer inputCustomerInfo() {
        String input = input(InputMessage.CREATE_CUSTOMER.getMessage()); // ex) 생성하실 회원의 이름과 보유금액을 입력해주세요.
        return Parser.parseToCustomerInfo(input);
    }

    public static long inputUniqueNumber() {
        return Long.parseLong(input(InputMessage.LOGIN_CUSTOMER.getMessage())); // ex) 접속하시는 회원의 번호를 입력해주세요.
    }

    public static String inputOption() {
        return input(InputMessage.DISPLAY_OPTION.getMessage());
    }

    public static void intro() {

    }
    public static List<OrderDto> inputMenu() {
        displayMessage(InputMessage.DISPLAY_MENU.getMessage());
        String orders = input(InputMessage.ORDER_MENU.getMessage());
        return Parser.parseToOrders(orders);
    }

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
                String.format(AdminErrorMessage.INVALID_INPUT.getMessage(), input)
            );
        }
    }

}