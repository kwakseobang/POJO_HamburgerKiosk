package menu.domain;

public enum MenuStatus {

    COUNT("개"),
    SOLD_OUT("품절"),
    WON("원"),
    ;

    private final String name;

    MenuStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}