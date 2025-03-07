package menu.domain;

public enum Set {

    DRINK("콜라"),
    POTATO("감자튀김"),
    ;

    private final String name;

    Set(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
