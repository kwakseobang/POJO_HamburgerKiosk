package payment.response;

public enum PaymentErrorMessage {

    NOT_ENOUGH_FUNDS("[ERROR] - 금액이 부족합니다."),
    ;

    private final String message;

    PaymentErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}