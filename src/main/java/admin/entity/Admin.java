package admin;

public class Admin {

    private final String name;
    private long amount;

    public Admin(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    // getter 를 지양하라는 이야기가 있다. 기껏 private 로 했는데 getter 로 내부 상태를 알게 하면 캡슐화의 의미가 있는가.
    // 그렇다고 아예 안 쓸수는 없다. 그래서 고민이다..
    public String getName() {
        return name;
    }

    public long getAmount() {
        return amount;
    }

    public void updateAmount(long amount) {
        this.amount = amount;
    }

}