package admin.domain;

import user.User;

public class Admin implements User {

    private final String id;
    private long amount;

    public Admin(String id, long amount) {
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
        this.amount += amount;
    }

}