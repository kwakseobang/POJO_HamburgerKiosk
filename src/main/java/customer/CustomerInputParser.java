package customer;

import admin.AdminErrorMessage;

public class CustomerInputParser {


    private static final String SEPARATOR = ",";

    public Customer parseToInfo(String input) {
        validateSeparator(input);

        String[] paredInput = input.split(SEPARATOR);
        long id = Long.parseLong(paredInput[0]);
        long amount = Long.parseLong(paredInput[1].trim()); // 공백 제거 후 변환

        return new Customer(id, amount);
    }

    private void validateSeparator(String input) {
        if (input.contains(SEPARATOR)) {
            return;
        }
        throw new IllegalArgumentException(AdminErrorMessage.INVALID_SEPARATOR.getMessage());
    }
}
