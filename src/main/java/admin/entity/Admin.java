package admin.entity;

public class Admin {

    private final String name;
    private long amount;

    public Admin(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    public void updateAmount(long amount) {
        this.amount += amount;
    }

}