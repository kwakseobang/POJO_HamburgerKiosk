package user;

public abstract class User {

    private final String id;
    protected long amount; // 자식에서 수정 가능하게 하기 위해 접근자 protected

    public User(String id, long amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public long getAmount() {
        return amount;
    }

    public abstract void updateAmount(long amount);

}