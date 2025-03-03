package admin.service;

import admin.Admin;
import admin.AdminErrorMessage;
import admin.AdminInput;
import admin.AdminRepository;

public class AdminService {

    private final AdminRepository adminRepository;
    private final AdminInput adminInput;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        this.adminInput = new AdminInput();
    }

    public Admin createAdmin() {
        Admin admin = adminInput.inputInfo();
        // TODO: 이 로직을 도메인에 넣어야 할 수도 있습니다.
        if (adminRepository.isExistName(admin.getName())) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_NAME.getMessage(), admin.getName()));
        }
        adminRepository.create(admin);
        return admin;
    }

    public Admin login() {
        String name = adminInput.inputName();
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
