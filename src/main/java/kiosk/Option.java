package kiosk;

public enum Option {
    EXIT(0),
    ADMIN_CREATE(1),
    ADMIN_LOGIN(2),
    CUSTOMER_CREATE(3),
    CUSTOMER_LOGIN(4);

    int num;

    Option(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    // 숫자 값으로 옵션을 찾을 수 있는 메서드
    public static Option getOption(int i) {
        for (Option option : Option.values()) {
            if (option.getNum() == i) {
                return option;
            }
        }
        throw new IllegalArgumentException(KioskErrorMessage.INVALID_OPTION.getMessage());
    }
}