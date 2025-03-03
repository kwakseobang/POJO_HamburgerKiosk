package order;

import customer.entity.Customer;
import io.Input;
import java.util.List;
import menu.Menu;
import menu.MenuErrorMessage;
import menu.MenuList;

public class Order {

    private MenuList menuList;
    private List<Menu> menus;

    // 주문 한다.
    // 들어온 메뉴가 존재하는지 확인한다.
    // 수량 체크를 한다.
    // 품절 체크를 한다.

    public Order() {
        this.menuList = new MenuList();
        menus = menuList.getMenuList();
    }

    // 메뉴를 주문한다.
    public void buy(Customer customer) {
        List<OrderDto> orders = Input.inputMenu(); // orderDto(메뉴이름, 수량)
        for (OrderDto order : orders) {
            Menu menu = findMenu(order.name());
            menu.updateQuantity(order.quantity());
        }

    }

    private Menu findMenu(String name) {

        return menus.stream()
            .filter(menu -> menu.getName().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.INVALID_MENU.getMessage(), name)
            ));
    }

}