package io.domain;

import static io.domain.OutPut.displayMessage;

import io.response.InputErrorMessage;
import io.response.InputMessage;
import java.util.List;
import java.util.Scanner;
import order.dto.OrderItemDto;
import parser.Parser;
import user.UserCreateDto;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static UserCreateDto inputUserInfo(String message) {
           return Parser.parseToUserInfo(input(message));
    }

    public static String inputUserId(String message) {
        return input(message);
    }

    public static int inputOption() {
        String option = input(InputMessage.DISPLAY_OPTION.getMessage());
        return Parser.parseToInteger(option);
    }

    public static String isExtraOrder() {
        return input(InputMessage.EXTRA_ORDER_MENU.getMessage());
    }

    public static List<OrderItemDto> inputMenu() {
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