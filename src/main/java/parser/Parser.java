package parser;

import static parser.Delimiter.COMMA;

import java.util.Arrays;

public class Parser {

    private Parser() {
    }

    // 결과값 예시: [치킨버거,7000,15,"치킨으로 만든 햄버거",햄버거] TODO: 유효성 검사 해야함.
    public static String[] parseToMenu(String menu) {
        return Arrays.stream(menu.split(COMMA.getDelimiter()))
            .map(String::trim) // 각 요소의 공백 제거
            .toArray(String[]::new);
    }

}