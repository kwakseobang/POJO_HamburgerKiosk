package admin.service;

import admin.entity.Admin;
import admin.response.AdminErrorMessage;
import admin.repository.AdminRepository;
import io.Input;

public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService() {
        this.adminRepository = new AdminRepository();
    }

    public void createAdmin() {
        Admin admin = Input.inputAdminInfo();
        // TODO: 이 로직을 도메인에 넣어야 할 수도 있습니다.
        if (adminRepository.isExistName(admin.getName())) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_NAME.getMessage(), admin.getName()));
        }
        adminRepository.create(admin);
    }

    public Admin login() {
        String name = Input.inputAdminName();
        Admin admin = adminRepository.findByAdmin(name);
        if (admin != null) {
            return admin;
        }
        throw new IllegalArgumentException(
            String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), name));
    }

    public void updateAmount(Admin admin, long amount) {
        admin.updateAmount(amount);
    }
}
