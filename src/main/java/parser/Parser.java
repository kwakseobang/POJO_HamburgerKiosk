package parser;

import static parser.Delimiter.COMMA;
import static parser.Delimiter.EMPTY;
import static parser.Delimiter.HYPHEN;
import static parser.Delimiter.LEFT_BRACKET;
import static parser.Delimiter.RIGHT_BRACKET;

import admin.dto.AdminCreateDto;
import customer.dto.CustomerCreateDto;
import io.response.InputErrorMessage;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;
import order.domain.Order;
import order.dto.OrderCreateDto;
import util.StringConverter;

public class Parser {

    private static final int ORDER_ITEMS_COUNT = 2;

    private Parser() {
    }

    public static String parseToBurgerName(String setName) {
        return setName.replace(Category.SET.getName(),EMPTY.getDelimiter());
    }

    // 결과값 예시: [치킨버거,7000,15,"치킨으로 만든 햄버거",햄버거]
    public static String[] parseToMenu(String menu) {
        return StringConverter.toStringArray(menu);
    }

    // TODO: parseToAdminInfo, parseToCustomerInfo 둘 다 유사하여 리팩토링 가능함.
    public static AdminCreateDto parseToAdminInfo(String input) {
        validateComma(input);
        // ex) [치킨버거,1000]
        String[] parsedInput = StringConverter.toStringArray(input);
        String name = parsedInput[0];
        long amount = parseToLong(parsedInput[1].trim());  // 공백 제거 후 변환

        return new AdminCreateDto(name, amount);
    }

    public static CustomerCreateDto parseToCustomerInfo(String input) {
        validateComma(input);

        String[] paredInput = StringConverter.toStringArray(input);
        long id = parseToLong(paredInput[0]);
        long amount = parseToLong(paredInput[1].trim());

        return new CustomerCreateDto(id, amount);
    }

    public static List<OrderCreateDto> parseToOrders(String orders) {
        validateBracket(orders);

        String[] parsedOrder = StringConverter.toStringArray(orders);
        List<OrderCreateDto> orderList = new ArrayList<>();

        for (String order : parsedOrder) {
            String[] orderItems = parseToOrder(order.trim());
            appendOrder(orderItems, orderList);
        }
        return orderList;
    }

    public static int parseToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
        }
    }

    private static String[] parseToOrder(String order) {
        validateBracket(order);
        String empty = EMPTY.getDelimiter();
        order = order.replace(
                LEFT_BRACKET.getDelimiter(), empty
            )
            .replace(RIGHT_BRACKET.getDelimiter(), empty
            ); // 대괄호 제거
        return order.split(HYPHEN.getDelimiter());
    }

    private static void appendOrder(String[] orderItems, List<OrderCreateDto> orderList) {
        if (orderItems.length == ORDER_ITEMS_COUNT) {
            String menuName = orderItems[0];
            long quantity = parseToLong(orderItems[1]);
            OrderCreateDto orderCreateDto = new OrderCreateDto(menuName, quantity);
            orderList.add(orderCreateDto);
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
    }

    // ,를 기준으로 나누고 다른 구분자가 있더라도 이름에 포함되면 유효하다.
    private static void validateComma(String input) {
        if (input.contains(COMMA.getDelimiter())) {
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_DELIMITER.getMessage());
    }

    private static void validateBracket(String input) {
        if (input.startsWith(LEFT_BRACKET.getDelimiter()) &&
            input.endsWith(RIGHT_BRACKET.getDelimiter())) {
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_DELIMITER.getMessage());
    }

    private static long parseToLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
        }
    }

}