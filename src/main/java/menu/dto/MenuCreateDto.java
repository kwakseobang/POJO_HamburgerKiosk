package menu.dto;

import menu.domain.Category;
import menu.domain.Menu;

public record MenuCreateDto(
    String name,
    long price,
    String quantity,
    String description,
    String category
) {

    public MenuCreateDto(String[] parsedData) {
        this(parsedData[0],
            Long.parseLong(parsedData[1]),
            parsedData[2],
            parsedData[3],
            parsedData[4]);
    }

    public Menu to() {
        return new Menu(
            name,
            price,
            quantity,
            description,
            Category.fromString(category)
        );
    }

}