package parser;

import static parser.Delimiter.COMMA;

import admin.entity.Admin;
import customer.entity.Customer;
import io.response.InputErrorMessage;
import java.util.Arrays;

public class Parser {

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

    private static void validateSeparator(String input) {
        if (input.contains(COMMA.getDelimiter())) {
            return;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_DELIMITER.getMessage());
    }

}