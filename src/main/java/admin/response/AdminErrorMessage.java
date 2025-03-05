package admin.response;

public enum AdminErrorMessage {

    DUPLICATION_ADMIN("[ERROR] - 해당 관리자는 이미 존재합니다: %s"),
    NOT_EXIST_ADMIN("[ERROR] - 존재하지 않는 관리자입니다: %s");

    private final String message;

    AdminErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}