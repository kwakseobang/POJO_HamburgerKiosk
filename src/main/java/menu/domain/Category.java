package menu.domain;

import menu.response.MenuErrorMessage;

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

    public static Category fromString(String value) {
        for (Category category : Category.values()) {
            if (category.getName().equals(value)) {
                return category;
            }
        }
        throw new IllegalArgumentException(
            MenuErrorMessage.NOT_EXIST_CATEGORY.getMessage() + value);
    }

}