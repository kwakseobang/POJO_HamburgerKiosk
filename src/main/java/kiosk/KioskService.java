package kiosk;

import static kiosk.Option.getOption;

import admin.domain.Admin;
import admin.service.AdminService;
import customer.domain.Customer;
import customer.service.CustomerService;
import file.service.FileService;
import io.domain.Input;
import io.domain.OutPut;
import io.response.InputErrorMessage;
import io.response.InputMessage;
import io.response.OutPutMessage;
import menu.domain.MenuList;
import menu.service.MenuService;
import order.dto.OrderDto;
import payment.service.PaymentService;
import receipt.ReceiptDto;
import receipt.service.ReceiptService;
import user.User;
import user.UserRepository;

public class KioskService {

    private static final String Y = "Y";
    private static final String N = "N";
    private final FileService fileService;
    private final MenuService menuService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final ReceiptService receiptService;
    private String adminName;

    public KioskService() {
        this.fileService = new FileService();
        this.menuService = new MenuService(fileService);
        UserRepository userRepository = new UserRepository();
        this.adminService = new AdminService(userRepository);
        this.customerService = new CustomerService(userRepository);
        this.paymentService = new PaymentService();
        this.receiptService = new ReceiptService();
    }

    public void start() {
        menuService.createMenuList();
        boolean isRunning = true;
        while (isRunning) {
            try {
                int optionNum = Input.inputOption();
                isRunning = selectOption(optionNum);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean selectOption(int optionNum) {
        Option option = getOption(optionNum);
        switch (option) {
            case EXIT -> {
                return exit();
            }
            case ADMIN_CREATE -> createAdmin();
            case ADMIN_LOGIN -> loginAdmin();
            case CUSTOMER_CREATE -> createCustomer();
            case CUSTOMER_LOGIN -> loginCustomer();
        }
        return true;
    }

    private boolean exit() {
        System.out.println(OutPutMessage.EXIT.getMessage());
        return false;
    }

    private void createAdmin() {
        String message = InputMessage.CREATE_ADMIN.getMessage();
        Admin admin = Input.inputUserInfo(message).toAdmin();

        adminService.create(admin);
    }

    private void loginAdmin() {
        String message = InputMessage.LOGIN_ADMIN.getMessage();
        String adminId = Input.inputUserId(message);

        adminName = adminService.login(adminId);
    }

    private void createCustomer() {
        String message = InputMessage.CREATE_CUSTOMER.getMessage();
        Customer customer = Input.inputUserInfo(message).toCustomer();

        customerService.create(customer);
    }

    private void loginCustomer() {
        User admin = adminService.findLoggedInAdminByName(adminName);

        String message = InputMessage.LOGIN_CUSTOMER.getMessage();
        String customerId = Input.inputUserId(message);
        User customer = customerService.login(customerId);

        processOrder(admin, customer);
        fileService.saveMenusToFile(menuService.readMenuList());
    }

    private void processOrder(User admin, User customer) {
        while (true) {
            try {
                OrderDto order = order(customer.getId());
                ReceiptDto receipt = paymentService.pay(order, admin, customer);
                displayReceipt(receipt, admin, customer);

                if (!isExtraOrder()) {
                    return;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private OrderDto order(String id) {
        OutPut.displayIntro(id, adminName);

        MenuList menuList = menuService.readMenuList();
        OutPut.displayMenuList(menuList.getMenuList());

        return customerService.order();
    }

    private void displayReceipt(ReceiptDto receipt, User admin, User customer) {
        receiptService.displayReceipt(receipt, admin, customer);
    }

    private boolean isExtraOrder() {
        String yesOrNo = Input.isExtraOrder();
        return switch (yesOrNo) {
            case Y -> true;
            case N -> false;
            default ->
                throw new IllegalArgumentException(InputErrorMessage.INVALID_INPUT.getMessage());
        };
    }

}