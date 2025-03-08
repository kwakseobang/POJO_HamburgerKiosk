package customer.domain;

import customer.response.CustomerErrorMessage;
import user.User;

public class Customer extends User {

    public Customer(String customerId, long amount) {
        super(customerId, amount);
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