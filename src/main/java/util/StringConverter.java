package util;

import static parser.Delimiter.COMMA;

import java.util.Arrays;

public class StringConverter {

    private StringConverter() {
    }

    public static String[] toStringArray(String input) {
        return Arrays.stream(input.split(COMMA.getDelimiter()))
            .map(String::trim) // 각 요소의 공백 제거
            .toArray(String[]::new);
    }

}