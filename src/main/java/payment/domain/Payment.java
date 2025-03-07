package payment.domain;

public class Payment {

    private final String name;
    private final long price;
    private final long quantity;

    private Payment(String name, long price, long quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // 생성 시 유효성 검사, 변환, 추가 로직 등 다양하게 사용 가능
    public static Payment createPayment(String name, long price, long quantity) {
        return new Payment(name,price,quantity);
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getPrice() {
        return price;
    }

}