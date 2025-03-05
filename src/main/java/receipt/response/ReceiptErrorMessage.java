package receipt.response;

public enum ReceiptErrorMessage {

    INVALID_RECEIPT("[ERROR] - 영수증 출력에 실패했습니다:"),
    ;

    private final String message;

    ReceiptErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}