package kiosk;

public abstract class KioskProcess {

    protected abstract void exit();
    protected abstract void createAdmin();
    protected abstract void loginAdmin();
    protected abstract void createCustomer();
    protected abstract void loginCustomer();

}