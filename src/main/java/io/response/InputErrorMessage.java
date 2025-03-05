package io.response;

public enum InputErrorMessage {

    EMPTY_INPUT("[ERROR] - 입력이 존재하지 않습니다."),
    INVALID_INPUT("[ERROR] - 올바르지 않은 형식으로 입력했습니다."),
    INVALID_DELIMITER("[ERROR] - 허용되지 않은 구분자입니다."),
    INVALID_OPTION("[ERROR] - 존재하지 않는 옵션입니다."),
    ;

    private final String message;

    InputErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}