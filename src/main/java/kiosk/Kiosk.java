package kiosk;

import static kiosk.Option.getOption;

import admin.service.AdminService;
import customer.CustomerService;
import io.OutPut;

public class Kiosk extends KioskProcess {

    private final AdminService adminService;
    private final CustomerService customerService;
    private final KioskInput kioskInput;

    public Kiosk(
        AdminService adminService,
        CustomerService customerService
    ) {
        this.adminService = adminService;
        this.customerService = customerService;
        this.kioskInput = new KioskInput();
    }

    public void start() {

        while (true) {
            int optionNum = Integer.parseInt(kioskInput.inputOption());
            selectOption(optionNum);
        }
    }

    // TODO: 반복문 돌림.
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

    @Override
    protected void exit() {
        OutPut.displayExit(); // 프로그램을 종료합니다.
        System.exit(1);
    }

    @Override
    protected void createAdmin() {
        adminService.createAdmin();
    }

    @Override
    protected void loginAdmin() {
        adminService.login();
    }

    @Override
    protected void createCustomer() {
        customerService.createCustomer();
    }

    @Override
    protected void loginCustomer() {
        customerService.login();
    }

}