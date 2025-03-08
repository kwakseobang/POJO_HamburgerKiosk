package admin.service;


import admin.domain.Admin;
import admin.repository.AdminRepository;
import admin.response.AdminErrorMessage;
import java.util.HashMap;

public class AdminService {

    private final AdminRepository adminRepository;
    private final HashMap<String, Admin> loggedInAdmin = new HashMap<>();

    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public void create(Admin admin) {
        validateDuplicateAdminName(admin.getId());
        adminRepository.create(admin);
    }

    public Admin findLoggedInAdminByName(String adminName) {
        if (loggedInAdmin.containsKey(adminName)) {
            return loggedInAdmin.get(adminName);
        }
        throw new IllegalArgumentException(AdminErrorMessage.UNAUTHORIZED_ADMIN.getMessage());
    }

    public String login(String name) {
        Admin admin = findAdminByName(name);
        loggedInAdmin.put(name, admin);
        return admin.getId();
    }

    private Admin findAdminByName(String name) {
        return adminRepository.findByAdmin(name)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(AdminErrorMessage.NOT_EXIST_ADMIN.getMessage(), name)
            ));
    }

    private void validateDuplicateAdminName(String name) {
        if (adminRepository.isExistByName(name)) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_ADMIN.getMessage(), name)
            );
        }
    }

}