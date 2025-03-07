package menu.domain;

public class Menu {

    private static final MenuList menuList = new MenuList();

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
        menuList.add(this);
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

    public long calculateQuantity(long newQuantity) {
        return this.quantity - newQuantity;
    }

    private boolean checkedSoldOut(Long quantity) {
        return quantity == 0;
    }

}