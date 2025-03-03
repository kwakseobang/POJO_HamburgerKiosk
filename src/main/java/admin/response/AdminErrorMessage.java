package admin.response;

public enum AdminErrorMessage {

    INVALID_INPUT("입력이 존재하지 않습니다."),
    DUPLICATION_NAME("해당 이름은 존재하는 이름입니다: %s"),
    INVALID_SEPARATOR("허용되지 않은 구분자입니다."),
    NOT_EXIST_NAME("존재하지 않는 관리자입니다: %s")
    ;

    private final String message;

    AdminErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}