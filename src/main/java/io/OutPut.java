package io;

import io.dto.UserDto;

public class OutPut {

    private OutPut() {
    }

    public static void displayOption() {
        String message =
            """
                0. 종료
                1. 관리자 생성
                2. 관리자 접속
                3. 회원 생성
                4. 회원 접속
                """;
        System.out.println(message);
    }

    public static void promptAdminInfo() {
        System.out.println("생성하실 관리자의 이름과 보유금액을 입력해주세요.");
    }

    public static void promptAdminName() {
        System.out.println("접속하시는 관리자의 이름을 입력해주세요.");
    }

    public static void promptMemberInfo() {
        System.out.println("생성하실 회원의 고유번호와 보유금액을 입력해주세요.");
    }

    public static void promptMemberUniqueNumber() {
        System.out.println("접속하시는 회원의 고유번호를 입력해주세요.");
    }

    public static void displayExit() {
        System.out.println("프로그램을 종료합니다.");
    }

    public static void displayIntro(UserDto userDto) {
        String message = String.format("""
            안녕하세요. %s님 햄버거 가게 입니다.
            현재 접속된 관리자는 %s 입니다.
            """, userDto.customerId(), userDto.adminName());
        System.out.println(message);
    }

    public static void promptOrderInfo() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요.");
    }

    public static void promptExtraOrderInfo() {
        System.out.println("감사합니다. 구매하고 싶은 다른 상품이 있나요?");
    }

    // TODO: 메뉴 안내 메시지
    public static void displayMenu() {

    }

    // TODO: 매개변수 에 값 추가 해야 됨 - 메뉴, 구매엑, 판매자, 구매자
    public static void displayReceipt() {

    }

}