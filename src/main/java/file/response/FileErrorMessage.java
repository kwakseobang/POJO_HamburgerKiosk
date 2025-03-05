package file.response;

public enum FileErrorMessage {

    EMPTY_FILE("[ERROR] - 파일이 존재하지 않습니다."),
    FAILED_READ_FILE("[ERROR] - 파일 읽기에 실패했습니다."),
    FAILED_WRITE_FILE("[ERROR] - 파일 쓰기에 실패했습니다."),
    ;

    private final String message;

    FileErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}