package kiosk;

import static kiosk.Option.getOption;

import admin.entity.Admin;
import admin.service.AdminService;
import customer.entity.Customer;
import customer.service.CustomerService;
import file.service.FileService;
import io.entity.Input;
import io.entity.OutPut;
import io.response.InputErrorMessage;
import io.response.OutPutMessage;
import java.util.List;
import menu.entity.Menu;
import menu.service.MenuService;

public class KioskService {

    private final FileService fileService;
    private final MenuService menuService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private String adminName;

    public KioskService() {
        this.fileService = new FileService();
        this.menuService = new MenuService(fileService);
        this.adminService = new AdminService(menuService);
        this.customerService = new CustomerService();
    }

    public void start() {
        fileLoad();
        while (true) {
            try {
                int optionNum = Input.inputOption();
                selectOption(optionNum);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void fileLoad() {
        menuService.createMenuList();
    }

    private void selectOption(int optionNum) {
        Option option = getOption(optionNum);
        switch (option) {
            case EXIT -> exit();  // TODO : 열거형으로 빼거나 반복문
            case ADMIN_CREATE -> createAdmin();
            case ADMIN_LOGIN -> loginAdmin();
            case CUSTOMER_CREATE -> createCustomer();
            case CUSTOMER_LOGIN -> loginCustomer();
        }
    }

    private void exit() {
        System.out.println(OutPutMessage.EXIT.getMessage());
        System.exit(1);
    }

    private void createAdmin() {
        adminService.create(Input.inputAdminInfo());
    }

    private void loginAdmin() {
        adminName = adminService.login(Input.inputAdminName());
    }

    private void createCustomer() {
        customerService.create(Input.inputCustomerInfo());
    }

    private void loginCustomer() {
        Admin admin = adminService.findLoggedInAdminByName(adminName);
        Customer customer = customerService.login(Input.inputUniqueNumber());
        processOrder(admin, customer);
        fileService.saveMenusToFile(adminService.readMenuList());
    }

    private void processOrder(Admin admin, Customer customer) {
        while (true) {
            try {
                OutPut.displayIntro(customer.getCustomerId(), adminName);
                List<Menu> menuList = adminService.readMenuList();
                customerService.order(menuList, admin, customer);
                if (!isExtraOrder()) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }

    private boolean isExtraOrder() {
        String yesOrNo = Input.isExtraOrder();
        if (yesOrNo.equals("Y")) {
            return true;
        }
        if (yesOrNo.equals("N")) {
            return false;
        }
        throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
    }

}