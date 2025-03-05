package kiosk;

import static kiosk.Option.getOption;

import admin.entity.Admin;
import admin.service.AdminService;
import customer.entity.Customer;
import customer.service.CustomerService;
import file.service.FileService;
import io.entity.Input;
import io.entity.OutPut;
import io.response.OutPutMessage;
import java.util.List;
import menu.entity.Menu;
import menu.service.MenuService;

public class KioskService {

    private final FileService fileService;
    private final MenuService menuService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private Admin admin;

    public KioskService() {
        this.fileService = new FileService();
        this.menuService = new MenuService(fileService);
        this.adminService = new AdminService(menuService);
        this.customerService = new CustomerService();
    }

    // 프로그램 시작
    public void start() {
        this.fileLoad();
        while (true) {
            int optionNum = Integer.parseInt(Input.inputOption());
            selectOption(optionNum);
        }

    }

    // 파일 로드
    private void fileLoad() {
        menuService.createMenuList();
    }

    private void selectOption(int optionNum) {
        Option option = getOption(optionNum);
        switch (option) {
            case EXIT -> exit();
            case ADMIN_CREATE -> createAdmin();
            case ADMIN_LOGIN -> loginAdmin();
            case CUSTOMER_CREATE -> createCustomer();
            case CUSTOMER_LOGIN -> loginCustomer();
        }
    }

    private void exit() {
        System.out.println(OutPutMessage.EXIT);
        System.exit(1);
    }

    private void createAdmin() {
        adminService.create(Input.inputAdminInfo());
    }

    private void loginAdmin() {
        this.admin = adminService.login(Input.inputAdminName());
    }

    private void createCustomer() {
        customerService.create(Input.inputCustomerInfo());
    }

    private void loginCustomer() {
        adminService.validateLoginStatus(admin);
        Customer customer = customerService.login(Input.inputUniqueNumber());
        while (true) {
            OutPut.displayIntro(customer.getCustomerId(), admin.getName());
            List<Menu> menuList = adminService.readMenuList();
            customerService.order(menuList,admin,customer);
            isExtraOrder();
        }
    }

    private void isExtraOrder() {
        if (!Input.isExtraOrder().equals("Y")) {
            exit();
        }
    }

}