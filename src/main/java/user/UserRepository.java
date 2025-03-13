package user;

import java.util.HashMap;
import java.util.Optional;

public class UserRepository {

    private static final HashMap<String, User> userinfo = new HashMap<>();

    public void create(User admin) {
        userinfo.put(admin.getId(), admin);
    }

    public boolean isExistById(String id) {
        return userinfo.containsKey(id);
    }

    public Optional<User> findByUser(String name) {
        return Optional.ofNullable(userinfo.get(name));
    }

}
