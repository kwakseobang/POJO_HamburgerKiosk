package admin.domain;

import user.User;

public class Admin extends User {

    public Admin(String id, long amount) {
        super(id, amount);
    }

    @Override
    public void updateAmount(long amount) {
        this.amount += amount;
    }

}