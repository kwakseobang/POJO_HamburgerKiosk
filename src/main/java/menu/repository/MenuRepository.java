package menu.repository;

import java.util.ArrayList;
import java.util.List;
import menu.entity.Menu;

public class MenuRepository {

    private List<Menu> menuList;

    public MenuRepository() {
        this.menuList = new ArrayList<>();
    }

    public void save(Menu menu) {
        menuList.add(menu);
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

}