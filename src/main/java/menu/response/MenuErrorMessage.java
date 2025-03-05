package menu.response;

public enum MenuErrorMessage {

    INVALID_MENU("[ERROR] - 존재하지 않는 상품입니다: %s"),
    INVALID_QUANTITY("[ERROR] - 재고 수량을 초과하여 구매할 수 없습니다."),
    INVALID_BUY("[ERROR] - 품절된 메뉴입니다: %s"),
    ;

    private final String message;

    MenuErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}