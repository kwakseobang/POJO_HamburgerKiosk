package admin.service;


import admin.domain.Admin;
import admin.response.AdminErrorMessage;
import java.util.HashMap;
import user.User;
import user.UserRepository;

public class AdminService {

    private final UserRepository userRepository;
    private final HashMap<String, User> loggedInAdmin = new HashMap<>();

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User admin) {
        validateDuplicateAdminName(admin.getId());
        userRepository.create(admin);
    }

    public User findLoggedInAdminByName(String adminName) {
        if (loggedInAdmin.containsKey(adminName)) {
            return loggedInAdmin.get(adminName);
        }
        throw new IllegalArgumentException(AdminErrorMessage.UNAUTHORIZED_ADMIN.getMessage());
    }

    public String login(String name) {
        User admin = findAdminByName(name);
        loggedInAdmin.put(name, admin);
        return admin.getId();
    }

    private User findAdminByName(String name) {
        return userRepository.findByUser(name)
            .orElseThrow(() -> new IllegalArgumentException(
                String.format(AdminErrorMessage.NOT_EXIST_ADMIN.getMessage(), name)
            ));
    }

    private void validateDuplicateAdminName(String name) {
        if (userRepository.isExistById(name)) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.DUPLICATION_ADMIN.getMessage(), name)
            );
        }
    }

}