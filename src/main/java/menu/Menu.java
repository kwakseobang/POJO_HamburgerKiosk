package menu;

public class Menu {

    private String name;
    private String price;
    private String quantity;
    private String description;
    private Category category;
    private boolean isSoldOut;


    public Menu(
        String name,
        String price,
        String quantity,
        String description,
        Category category
    ) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.category = category;
        this.isSoldOut = false;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void updateQuantity(String newQuantity) {
        String quantity = calculateQuantity(this.quantity, newQuantity);
        if (quantity.equals("0")) {
            this.quantity = "품절";
            return;
        }
        this.quantity = quantity;
        ;
    }

    public void isSoldOut() {
        this.isSoldOut = true;
    }

    private String calculateQuantity(String oldQuantity, String newQuantity) {
        long quantity = Long.parseLong(oldQuantity) - Long.parseLong(newQuantity);
        if (quantity < 0) {
            throw new IllegalArgumentException(MenuErrorMessage.INVALID_QUANTITY.getMessage());
        }
        return String.valueOf(quantity);
    }

}