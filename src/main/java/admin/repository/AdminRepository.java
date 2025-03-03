package admin;

import java.util.HashMap;

public class AdminRepository {

    private static final HashMap<String, Admin> adminInfo = new HashMap<>();

    public void create(Admin admin) {
        adminInfo.put(admin.getName(), admin);
    }

    public boolean isExistName(String name) {
        return adminInfo.containsKey(name);
    }

    public Admin findByAdmin(String name) {
        return adminInfo.get(name);
    }

}