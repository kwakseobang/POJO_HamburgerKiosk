package customer.entity;

public class Customer {

    private final long customerId;
    private long amount;

    public Customer(long customerId, long amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getAmount() {
        return amount;
    }

    public void updateAmount(long amount) {
        this.amount = amount;
    }

}