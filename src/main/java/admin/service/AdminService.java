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
        if (adminRepository.isExistName(admin.getName())) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_ADMIN.getMessage(), admin.getName())
            );
        }
        adminRepository.create(admin);
    }

    public Admin login(String name) {
        return adminRepository.findByAdmin(name)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(AdminErrorMessage.NOT_EXIST_ADMIN.getMessage(), name)
            ));
    }

    public void validateLoginStatus(Admin admin) {
        if (admin == null) {
            throw new RuntimeException(AdminErrorMessage.UNAUTHORIZED_ADMIN.getMessage());
        }
    }

}