package kiosk;

import io.response.InputErrorMessage;

public enum Option {
    EXIT(0),
    ADMIN_CREATE(1),
    ADMIN_LOGIN(2),
    CUSTOMER_CREATE(3),
    CUSTOMER_LOGIN(4);

    private final int num;

    Option(int num) {
        this.num = num;
    }

    public int getOptionNum() {
        return num;
    }

    public static Option getOption(int num) {
        for (Option option : Option.values()) {
            if (option.getOptionNum() == num) {
                return option;
            }
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_OPTION.getMessage());
    }

}