package io.response;

public enum OutPutMessage {

    EXIT("프로그램을 종료합니다."),
    DISPLAY_INTRO("""
        안녕하세요. %s님 햄버거 가게 입니다.
        현재 접속된 관리자는 %s 입니다.
        """),
    DISPLAY_MENU("""
        -%s, %s, %s, %s, %s
        """),
    DISPLAY_RECEIPT_HEADER("""
        =====================
        상품명       수량    금액
        """),
    DISPLAY_RECEIPT_BODY("""
        %s       %s    %s
        """
    ),
    DISPLAY_RECEIPT_FOOTER("""
        =====================
        총구매액    %s   %s
        =====================
        판매자: %s, %s
        구매자: %s, %s
        """
    ),
    ;
    private final String message;

    OutPutMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}