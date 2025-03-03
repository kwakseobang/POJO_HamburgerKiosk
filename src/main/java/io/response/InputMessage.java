package io.response;

public enum InputMessage {

    CREATE_ADMIN("생성하실 관리자의 이름과 보유금액을 입력해주세요."),
    LOGIN_ADMIN("접속하시는 관리자의 이름을 입력해주세요."),
    CREATE_CUSTOMER("생성하실 회원의 고유번호와 보유금액을 입력해주세요."),
    LOGIN_CUSTOMER("접속하시는 회원의 고유번호를 입력해주세요."),
    ORDER_MENU("구매하실 상품명과 수량을 입력해 주세요."),
    EXTRA_ORDER_MENU("감사합니다. 구매하고 싶은 다른 상품이 있나요?"),
    EXIT("프로그램을 종료합니다."),
    DISPLAY_OPTION("""
        0. 종료
        1. 관리자 생성
        2. 관리자 접속
        3. 회원 생성
        4. 회원 접속
        """),
    DISPLAY_INTRO("""
        안녕하세요. %s님 햄버거 가게 입니다.
        현재 접속된 관리자는 %s 입니다.
        """),
    // TODO: 메뉴 안내 메시지
    DISPLAY_MENU(""),
    // TODO: 매개변수 에 값 추가 해야 됨 - 메뉴, 구매엑, 판매자, 구매자
    DISPLAY_RECEIPT(""),
    ;

    private final String message;

    InputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    }
