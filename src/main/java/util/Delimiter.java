package util;

public enum Delimiter {

    COMMA(","),
    EMPTY(""),
    LEFT_BRACKET("["),
    RIGHT_BRACKET("]"),
    HYPHEN("-"),
    ;


    private final String delimiter;

    Delimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
