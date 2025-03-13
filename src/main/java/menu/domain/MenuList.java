package menu.domain;

import java.util.ArrayList;
import java.util.List;
import menu.response.MenuErrorMessage;

public class MenuList {

    private final List<Menu> menuList;

    public MenuList() {
        this.menuList = new ArrayList<>();
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void add(Menu menu) {
        menuList.add(menu);
    }

    public Menu findByMenu(String name) {
        return menuList.stream()
            .filter(menu -> menu.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.NOT_EXIST_MENU.getMessage(), name)
            ));
    }

}