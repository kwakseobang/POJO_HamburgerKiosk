import admin.AdminRepository;
import admin.service.AdminService;
import customer.CustomerRepository;
import customer.CustomerService;
import kiosk.Kiosk;

public class Main {

    public static void main(String[] args) {
        final AdminService adminService = new AdminService(new AdminRepository());
        final CustomerService customerService = new CustomerService(new CustomerRepository());
        Kiosk kiosk = new Kiosk(adminService,customerService);

        kiosk.start();
    }
}