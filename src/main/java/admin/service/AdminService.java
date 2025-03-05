package admin.service;


import admin.entity.Admin;
import admin.repository.AdminRepository;
import admin.response.AdminErrorMessage;
import io.entity.Input;
import java.util.List;
import menu.entity.Menu;
import menu.service.MenuService;

public class AdminService {

    private MenuService menuService;
    private AdminRepository adminRepository;

    public AdminService(MenuService menuService, AdminRepository adminRepository) {
        this.menuService = menuService;
        this.adminRepository = adminRepository;
    }

    public List<Menu> readMenuList() {
        return menuService.readMenuList();
    }

    public void create() {
        Admin admin = Input.inputAdminInfo();
        if (adminRepository.isExistName(admin.getName())) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_ADMIN.getMessage(), admin.getName())
            );
        }
        adminRepository.create(admin);
    }

    public Admin login() {
        String name = Input.inputAdminName();
        return adminRepository.findByAdmin(name)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(AdminErrorMessage.NOT_EXIST_ADMIN.getMessage(), name)
            ));
    }

}