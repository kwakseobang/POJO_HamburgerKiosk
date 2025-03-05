package admin.service;


import admin.entity.Admin;
import admin.repository.AdminRepository;
import admin.response.AdminErrorMessage;
import java.util.List;
import menu.entity.Menu;
import menu.service.MenuService;

public class AdminService {

    private MenuService menuService;
    private AdminRepository adminRepository;

    public AdminService(MenuService menuService) {
        this.menuService = menuService;
        this.adminRepository = new AdminRepository();
    }

    public List<Menu> readMenuList() {
        return menuService.readMenuList();
    }

    public void create(Admin admin) {
        validateDuplicateAdminName(admin.getName());
        adminRepository.create(admin);
    }

    public Admin findLoggedInAdminByName(String adminName) {
        return adminRepository.findLoggedInAdminByName(adminName);
    }

    public String login(String name) {
        Admin admin = findAdminByName(name);
        adminRepository.registerLoggedInAdmin(admin);
        return admin.getName();
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