package parser;

import static parser.Delimiter.COMMA;
import static parser.Delimiter.EMPTY;
import static parser.Delimiter.HYPHEN;
import static parser.Delimiter.LEFT_BRACKET;
import static parser.Delimiter.RIGHT_BRACKET;

import admin.entity.Admin;
import customer.entity.Customer;
import io.response.InputErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import order.entity.Order;

public class Parser {

    private static final int ORDER_COUNT = 2; // TODO: 네이밍 좀 더 정확하게 바꿔야 될 거 같음.

    private Parser() {
    }

    // 결과값 예시: [치킨버거,7000,15,"치킨으로 만든 햄버거",햄버거] TODO: 유효성 검사 해야함.
    public static String[] parseToMenu(String menu) {
        return Arrays.stream(menu.split(COMMA.getDelimiter()))
            .map(String::trim) // 각 요소의 공백 제거
            .toArray(String[]::new);
    }

    // TODO: parseToAdminInfo, parseToCustomerInfo 둘 다 유사하여 리팩토링 가능함.
    public static Admin parseToAdminInfo(String input) {
        validateSeparator(input);

        String[] parsedInput = input.split(COMMA.getDelimiter());
        String name = parsedInput[0];
        long amount = Long.parseLong(parsedInput[1].trim()); // 공백 제거 후 변환

        return new Admin(name, amount);
    }

    public static Customer parseToCustomerInfo(String input) {
        validateSeparator(input);

        String[] paredInput = input.split(COMMA.getDelimiter());
        long id = Long.parseLong(paredInput[0]);
        long amount = Long.parseLong(paredInput[1].trim()); // 공백 제거 후 변환

        return new Customer(id, amount);
    }

    public static List<Order> parseToOrders(String orders) {
        String[] parsedOrder = orders.split(COMMA.getDelimiter());
        List<Order> orderList = new ArrayList<>();

        for (String order : parsedOrder) {
            String[] orderItems = parseToOrder(order.trim());
            appendOrder(orderItems, orderList);
        }
        return orderList;
    }

    private static String[] parseToOrder(String order) {
        String empty = EMPTY.getDelimiter();
        order = order.replace(
                LEFT_BRACKET.getDelimiter(), empty
            )
            .replace(RIGHT_BRACKET.getDelimiter(), empty
            ); // 대괄호 제거
        return order.split(HYPHEN.getDelimiter());
    }

    private static void appendOrder(String[] orderItems, List<Order> orderList) {
        if (orderItems.length == ORDER_COUNT) {
            String menuName = orderItems[0];
            long quantity = Long.parseLong(orderItems[1]); // TODO: long 변환 유틸리티 메서드 따로 정의
            Order order = new Order(menuName, quantity);
            orderList.add(order);
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
    }

    private static void validateSeparator(String input) {
        if (input.contains(COMMA.getDelimiter())) {
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_DELIMITER.getMessage());
    }

}