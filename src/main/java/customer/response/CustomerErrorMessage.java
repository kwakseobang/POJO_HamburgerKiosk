package customer.response;

public enum CustomerErrorMessage {

    DUPLICATION_CUSTOMER("해당 회원은 이미 존재합니다: %s"),
    NOT_EXIST_CUSTOMER("존재하지 않는 회원입니다: %s");

    private final String message;

    CustomerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}