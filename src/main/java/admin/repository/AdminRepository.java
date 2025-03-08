package admin.repository;

import admin.domain.Admin;
import java.util.HashMap;
import java.util.Optional;

public class AdminRepository {

    private static final HashMap<String, Admin> adminInfo = new HashMap<>();

    public void create(Admin admin) {
        adminInfo.put(admin.getId(), admin);
    }

    public boolean isExistByName(String name) {
        return adminInfo.containsKey(name);
    }

    public Optional<Admin> findByAdmin(String name) {
        return Optional.ofNullable(adminInfo.get(name));
    }

}