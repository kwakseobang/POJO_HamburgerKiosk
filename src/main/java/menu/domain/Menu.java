package menu.domain;

import menu.response.MenuErrorMessage;

public class Menu {

    private static final MenuList menuList = new MenuList();

    private final String name;
    private final long price;
    private Long quantity;
    private final String description;
    private final Category category;
    private boolean isSoldOut;

    private Menu(
        String name,
        long price,
        Long quantity,
        String description,
        Category category,
        boolean isSoldOut
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.isSoldOut = isSoldOut;
        menuList.add(this);
    }

    public static Menu createMenu(String[] parsedData) {
        String name = parsedData[0];
        long price = Long.parseLong(parsedData[1]);
        long quantity = Long.parseLong(parsedData[2]);
        String description = parsedData[3];
        String category = parsedData[4];

        return new Menu(
            name,
            price,
            quantity,
            description,
            Category.fromString(category),
            checkedSoldOut(quantity) // 초기에 품절 됐을 경우 가 있기에 체크
        );
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

    public static Menu findByMenu(String name) {
        return menuList.findByMenu(name);
    }

    public void updateQuantity(long newQuantity) {
        long quantity = calculateQuantity(newQuantity); // 함수가 있는 김에 사용함.
        if (quantity == 0) {
            updateSoldOutStatus();
        }
        this.quantity = quantity;
    }

    public Menu validateOrderedMenu(long orderedQuantity) {
        validateQuantity(this, orderedQuantity);
        if (category.getName().equals(Category.SET.getName())) {
            validateSet(orderedQuantity);
        }
        return this;
    }

    private void validateQuantity(Menu menu, long orderedQuantity) {
        if (menu.isSoldOut()) {
            throw new IllegalArgumentException(
                String.format(MenuErrorMessage.INVALID_BUY.getMessage(), this.name));
        }
        if (calculateQuantity(orderedQuantity) < 0) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
    }

    private void validateSet(long orderedQuantity) {
        Menu drink = Menu.findByMenu(Set.DRINK.getName());
        Menu potato = Menu.findByMenu(Set.POTATO.getName());
        validateQuantity(drink, orderedQuantity);
        validateQuantity(potato, orderedQuantity);
    }

    private void updateSoldOutStatus() {
        this.isSoldOut = true;
    }

    private long calculateQuantity(long newQuantity) {
        return this.quantity - newQuantity;
    }

    private static boolean checkedSoldOut(Long quantity) {
        return quantity == 0;
    }

}