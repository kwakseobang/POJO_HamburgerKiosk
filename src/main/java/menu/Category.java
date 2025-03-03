package menu;

public enum Category {
    SET("세트"),
    HAMBURGER("햄버거"),
    SIDE("사이드"),
    DRINK("음료수"),
    ;

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
