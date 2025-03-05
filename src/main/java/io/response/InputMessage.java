package io.response;

public enum InputMessage {

    CREATE_ADMIN("생성하실 관리자의 이름과 보유금액을 입력해주세요."),
    LOGIN_ADMIN("접속하시는 관리자의 이름을 입력해주세요."),
    CREATE_CUSTOMER("생성하실 회원의 고유번호와 보유금액을 입력해주세요."),
    LOGIN_CUSTOMER("접속하시는 회원의 고유번호를 입력해주세요."),
    ORDER_MENU("\n구매하실 상품명과 수량을 입력해 주세요. (예: [치킨버거-3],[불고기버거세트-2])"),
    EXTRA_ORDER_MENU("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
    DISPLAY_OPTION("""
        0. 종료
        1. 관리자 생성
        2. 관리자 접속
        3. 회원 생성
        4. 회원 접속
        """),
    ;

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}