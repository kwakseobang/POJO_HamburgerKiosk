package user;

import admin.domain.Admin;
import customer.domain.Customer;

public record UserCreateDto(String id, long amount) implements AdminBase, CustomerBase {

    @Override
    public Admin toAdmin() {
        return new Admin(id, amount);
    }

    @Override
    public Customer toCustomer() {
        return new Customer(id, amount);
    }

}