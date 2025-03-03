package kiosk;

public enum KioskErrorMessage {

    INVALID_OPTION("존재하지 않는 옵션입니다.")
    ;

    private final String message;

    KioskErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}