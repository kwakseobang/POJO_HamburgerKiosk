package user;

import admin.domain.Admin;
import customer.domain.Customer;

public record UserCreateDto(String id, long amount) {

    public Admin toAdmin() {
        return new Admin(id, amount);
    }

    public Customer toCustomer() {
        return new Customer(id, amount);
    }

}