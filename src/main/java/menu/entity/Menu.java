package menu.entity;

public class Menu {

    private String name;
    private long price;
    private String quantity;
    private String description;
    private Category category;
    private boolean isSoldOut;

    public Menu(String[] parsedMenuItem) {
        this.name = parsedMenuItem[0];
        this.price = Long.parseLong(parsedMenuItem[1]);
        this.quantity = parsedMenuItem[2];
        this.description = parsedMenuItem[3];
        this.category = Category.fromString(parsedMenuItem[4]);
        this.isSoldOut = false;
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

    public String getCategory() {
        return category.getName();
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void updateSoldOut() {
        this.isSoldOut = true;
    }

//    public void updateQuantity(String newQuantity) {
//        String quantity = calculateQuantity(this.quantity, newQuantity);
//        if (quantity.equals("0")) {
//            isSoldOut();
//            this.quantity = "품절";
//            return;
//        }
//        this.quantity = quantity;
//    }

}