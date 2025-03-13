package customer.domain;

import customer.response.CustomerErrorMessage;
import user.User;

public class Customer implements User {

    private final String id;
    private long amount;

    public Customer(String id, long amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getAmount() {
        return amount;
    }

    @Override
    public void updateAmount(long amount) {
        long newAmount = this.amount - amount;
        if (newAmount > 0) {
            this.amount = newAmount;
            return;
        }
        throw new IllegalArgumentException(CustomerErrorMessage.NOT_ENOUGH_MONEY.getMessage());
    }

}