package util;

import static util.Delimiter.COMMA;
import static util.Delimiter.EMPTY;
import static util.Delimiter.HYPHEN;
import static util.Delimiter.LEFT_BRACKET;
import static util.Delimiter.RIGHT_BRACKET;

import admin.response.AdminErrorMessage;
import admin.entity.Admin;
import customer.entity.Customer;
import java.util.ArrayList;
import java.util.List;
import order.OrderDto;

public class Parser {

    private static final int ORDER_COUNT = 2; // TODO: 네이밍 좀 더 정확하게 바꿔야 될 거 같음.

    private Parser() {
    }

    // TODO: 둘 다 유사하여 리팩토링 가능함.
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

    public static List<OrderDto> parseToOrders(String orders) {
        String[] parsedOrder = orders.split(COMMA.getDelimiter());
        List<OrderDto> orderList = new ArrayList<>();

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

    private static void appendOrder(String[] orderItems, List<OrderDto> orderList) {
        if (orderItems.length == ORDER_COUNT) {
            String menuName = orderItems[0];
            String quantity = orderItems[1];
            OrderDto orderDto = new OrderDto(menuName, quantity);
            orderList.add(orderDto);
            return;
        }
        throw new IllegalArgumentException("잘못된 입력 값입니다.");
    }

    private static void validateSeparator(String input) {
        if (input.contains(COMMA.getDelimiter())) {
            return;
        }
        throw new IllegalArgumentException(AdminErrorMessage.INVALID_SEPARATOR.getMessage());
    }

}