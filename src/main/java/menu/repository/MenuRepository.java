package menu.repository;

import menu.domain.Menu;
import menu.domain.MenuList;

public class MenuRepository {

    private final MenuList menuList;

    public MenuRepository() {
        menuList = new MenuList();
    }

    public void save(Menu menu) {
        menuList.add(menu);
    }

    public MenuList getMenuList() {
        return menuList;
    }

}