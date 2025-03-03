package admin;

public class InputParser {

    private static final String SEPARATOR = ",";

    public Admin parseToAdminInfo(String input) {
        validateSeparator(input);

        String[] paredInput = input.split(SEPARATOR);
        String name = paredInput[0];
        long amount = Long.parseLong(paredInput[1].trim()); // 공백 제거 후 변환

        return new Admin(name, amount);
    }

    private void validateSeparator(String input) {
        if (input.contains(SEPARATOR)) {
            return;
        }
        throw new IllegalArgumentException(AdminErrorMessage.INVALID_SEPARATOR.getMessage());
    }

}