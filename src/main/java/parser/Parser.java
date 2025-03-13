package parser;

import static parser.Delimiter.COMMA;
import static parser.Delimiter.EMPTY;
import static parser.Delimiter.HYPHEN;
import static parser.Delimiter.LEFT_BRACKET;
import static parser.Delimiter.RIGHT_BRACKET;

import io.response.InputErrorMessage;
import java.util.ArrayList;
import java.util.List;
import menu.domain.Category;
import order.dto.OrderItemDto;
import user.UserCreateDto;
import util.StringConverter;

public class Parser {

    private static final int ORDER_ITEMS_COUNT = 2;

    private Parser() {
    }

    public static String parseToBurgerName(String setName) {
        return setName.replace(Category.SET.getName(), EMPTY.getDelimiter());
    }

    // 결과값 예시: [치킨버거,7000,15,"치킨으로 만든 햄버거",햄버거]
    public static String[] parseToMenu(String menu) {
        return StringConverter.toStringArray(menu);
    }

    public static UserCreateDto parseToUserInfo(String input) {
        validateComma(input);
        String[] parsedInput = StringConverter.toStringArray(input);
        String id = parsedInput[0];
        long amount = parseToLong(parsedInput[1].trim());

        return new UserCreateDto(id, amount);
    }

    public static List<OrderItemDto> parseToOrders(String orders) {
        validateBracket(orders);

        String[] parsedOrder = StringConverter.toStringArray(orders);
        List<OrderItemDto> orderList = new ArrayList<>();

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

    private static void appendOrder(String[] orderItems, List<OrderItemDto> orderList) {
        if (orderItems.length == ORDER_ITEMS_COUNT) {
            String menuName = orderItems[0];
            long quantity = parseToLong(orderItems[1]);
            OrderItemDto orderItemDto = new OrderItemDto(menuName, quantity);
            orderList.add(orderItemDto);
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