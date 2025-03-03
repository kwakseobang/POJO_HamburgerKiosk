package kiosk;

import static kiosk.Option.getOption;

import admin.entity.Admin;
import admin.service.AdminService;
import customer.entity.Customer;
import customer.service.CustomerService;
import io.Input;
import io.response.InputMessage;

public class Kiosk extends KioskProcess {

    private final AdminService adminService;
    private final CustomerService customerService;
    private Admin admin;
    private Customer customer;

    public Kiosk() {
        this.adminService = new AdminService();
        this.customerService = new CustomerService();
    }

    public void start() {

        while (true) {
            int optionNum = Integer.parseInt(Input.inputOption());
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
        System.out.println(InputMessage.EXIT);
        System.exit(1);
    }

    @Override
    protected void createAdmin() {
        adminService.createAdmin();
    }

    @Override
    protected void loginAdmin() {
       this.admin = adminService.login();
    }

    @Override
    protected void createCustomer() {
        customerService.createCustomer();
    }

    @Override
    protected void loginCustomer() {
        this.customer = customerService.login();

    }

}