package io.domain;

import static io.domain.OutPut.displayMessage;

import io.response.InputErrorMessage;
import io.response.InputMessage;
import java.util.List;
import java.util.Scanner;
import order.dto.OrderCreateDto;
import parser.Parser;
import user.UserCreateDto;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static UserCreateDto inputUserInfo(InputMessage inputMessage) {
           return Parser.parseToUserInfo(input(inputMessage.getMessage()));
    }

    public static String inputAdminName() {
        return input(InputMessage.LOGIN_ADMIN.getMessage());
    }

    public static String inputUniqueNumber() {
        return input(InputMessage.LOGIN_CUSTOMER.getMessage());
    }

    public static int inputOption() {
        return Parser.parseToInteger(input(InputMessage.DISPLAY_OPTION.getMessage()));
    }

    public static String isExtraOrder() {
        return input(InputMessage.EXTRA_ORDER_MENU.getMessage());
    }

    public static List<OrderCreateDto> inputMenu() {
        String orders = input(InputMessage.ORDER_MENU.getMessage());
        return Parser.parseToOrders(orders);
    }

    private static String input(String message) {
        displayMessage(message);
        String input = sc.nextLine();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                String.format(InputErrorMessage.EMPTY_INPUT.getMessage())
            );
        }
    }

}