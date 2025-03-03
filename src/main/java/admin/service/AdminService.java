package admin.service;

import admin.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin login(String name);
    void updateAmount(Admin admin, long amount);

}