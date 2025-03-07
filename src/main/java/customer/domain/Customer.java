package customer.domain;

import customer.response.CustomerErrorMessage;

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
        long newAmount = this.amount - amount;
        if (newAmount > 0) {
            this.amount = newAmount;
            return;
        }
        throw new IllegalArgumentException(CustomerErrorMessage.NOT_ENOUGH_MONEY.getMessage());
    }

}