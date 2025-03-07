package menu.domain;

import java.util.List;
import menu.response.MenuErrorMessage;
import parser.Parser;

public class Menu {

    private String name;
    private long price;
    private Long quantity;
    private String description;
    private Category category;
    private boolean isSoldOut;

    public Menu(
        String name,
        long price,
        Long quantity,
        String description,
        Category category
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.isSoldOut = checkedSoldOut(quantity); // 초기에 품절 됐을 경우 가 있기에 체크
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category.getName();
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void updateSoldOutStatus() {
        this.isSoldOut = true;
    }

    public static Menu findByMenu(String name, List<Menu> menuList) {
        return menuList.stream()
            .filter(menu -> menu.getName().equals(name)).findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(MenuErrorMessage.NOT_EXIST_MENU.getMessage(), name)
            ));
    }

    public void updateQuantity(long newQuantity) {
        long quantity = calculateQuantity(newQuantity);
        if (quantity == 0) {
            updateSoldOutStatus();
            return;
        }
        this.quantity = quantity;
    }

    public long calculateQuantity(long newQuantity) {
        return this.quantity - newQuantity;
    }

    private boolean checkedSoldOut(Long quantity) {
        return quantity == 0;
    }
//    private String isSet(String setName) {
//        String burgerName = Parser.parseToBurgerName(setName);
////        findByMenu()
//    }

    // 메뉴 리스트 객체를 따로 만든다. static 으로
    // 메뉴에서 메뉴 리스트 객체를 참조한다.
    // 메뉴가 세트일 경우 세트를 제외한 ㅇ이름만 추출해서 뽑아내고
    // 그 개수를 세트 메뉴 객체 개수에 주입해서 같은 곳을 바라보게 만든다.
}