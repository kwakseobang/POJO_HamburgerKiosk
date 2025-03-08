package admin.service;


import admin.domain.Admin;
import admin.repository.AdminRepository;
import admin.response.AdminErrorMessage;

public class AdminService {

    private AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public void create(Admin admin) {
        validateDuplicateAdminName(admin.getId());
        adminRepository.create(admin);
    }

    public Admin findLoggedInAdminByName(String adminName) {
        return adminRepository.findLoggedInAdminByName(adminName);
    }

    public String login(String name) {
        Admin admin = findAdminByName(name);
        adminRepository.registerLoggedInAdmin(admin);
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