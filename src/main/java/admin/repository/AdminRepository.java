package admin.repository;

import admin.entity.Admin;
import admin.response.AdminErrorMessage;
import java.util.HashMap;
import java.util.Optional;

public class AdminRepository {

    private static final HashMap<String, Admin> adminInfo = new HashMap<>();
    private Admin loggedInAdmin;

    public void create(Admin admin) {
        adminInfo.put(admin.getName(), admin);
    }

    public boolean isExistByName(String name) {
        return adminInfo.containsKey(name);
    }

    public Optional<Admin> findByAdmin(String name) {
        return Optional.ofNullable(adminInfo.get(name));
    }

    public Admin findLoggedInAdminByName(String name) {
        String loggedInAdminName = loggedInAdmin.getName();
        if (loggedInAdminName.equals(name)) {
            return loggedInAdmin;
        }
        throw new RuntimeException(AdminErrorMessage.UNAUTHORIZED_ADMIN.getMessage());
    }

    public void registerLoggedInAdmin(Admin admin) {
        this.loggedInAdmin = admin;
    }

}