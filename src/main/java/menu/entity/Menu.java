package menu.entity;

public class Menu {

    private String name;
    private long price;
    private String quantity;
    private String description;
    private Category category;
    private boolean isSoldOut;

    public Menu(
        String name,
        long price,
        String quantity,
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

    public String getQuantity() {
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
        this.quantity = MenuStatus.SOLD_OUT.name();
    }


    public void updateQuantity(long newQuantity) {
        long quantity = calculateQuantity(newQuantity);
        if (quantity == 0) {
            updateSoldOutStatus();
            return;
        }
        this.quantity = String.valueOf(quantity);
    }

    public long calculateQuantity(long newQuantity) {
        return Long.parseLong(this.quantity) - newQuantity;
    }

    private boolean checkedSoldOut(String quantity) {
        if (quantity.equals(MenuStatus.SOLD_OUT.name())) {
            return true;
        }
        return false;
    }

}