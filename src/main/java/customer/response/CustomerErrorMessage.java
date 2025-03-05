package customer.response;

public enum CustomerErrorMessage {

    DUPLICATION_CUSTOMER("[ERROR] - 해당 회원은 이미 존재합니다: %s"),
    NOT_EXIST_CUSTOMER("[ERROR] - 존재하지 않는 회원입니다: %s"),
    NOT_ENOUGH_MONEY("[ERROR] - 잔액이 부족합니다."),
    ;

    private final String message;

    CustomerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}