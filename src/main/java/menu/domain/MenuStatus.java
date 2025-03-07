package menu.domain;

import menu.response.MenuErrorMessage;

public enum MenuStatus {

    COUNT("개"),
    SOLD_OUT("품절"),
    WON("원"),
    ;

    private String name;

    MenuStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MenuStatus fromString(String value) {
        for (MenuStatus menuStatus : MenuStatus.values()) {
            if (menuStatus.name.equals(value)) {
                return menuStatus;
            }
        }
        throw new IllegalArgumentException(
            MenuErrorMessage.NOT_EXIST_MENU_STATUS.getMessage() + value);
    }

}