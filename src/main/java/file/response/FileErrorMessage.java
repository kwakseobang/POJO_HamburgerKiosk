package file.response;

public enum FileErrorMessage {

    FAILED_READ_FILE("ERROR] - 파일 불러오기에 실패했습니다."),
    FAILED_WRITE_FILE("ERROR] - 파일 쓰기에 실패했습니다."),
    ;

    private final String message;

    FileErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}