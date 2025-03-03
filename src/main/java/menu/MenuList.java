package menu;


import java.util.List;

public class MenuList {

    private List<Menu> menuList;

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void append(Menu menu) {
        menuList.add(menu);
    }

}