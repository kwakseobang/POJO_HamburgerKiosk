package menu.repository;

import java.util.List;
import menu.entity.Menu;

public class MenuRepository {

    private List<Menu> menuList;

    public void save(Menu menu) {
        menuList.add(menu);
    }

    public List<Menu> readMenuList() {
        return menuList;
    }

}