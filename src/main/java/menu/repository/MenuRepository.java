package menu.repository;

import menu.domain.MenuList;
import menu.dto.MenuCreateDto;

public class MenuRepository {

    private MenuList menuList;

    public MenuRepository() {
        menuList = new MenuList();
    }

    public void save(MenuCreateDto menuCreateDto) {
        menuList.add(menuCreateDto.to());
    }

    public MenuList getMenuList() {
        return menuList;
    }

}