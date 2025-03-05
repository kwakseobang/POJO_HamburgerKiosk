package customer.dto;

import customer.entity.Customer;

public record CustomerCreateDto(long id, long amount) {

    public Customer to() {
        return new Customer(id, amount);
    }

}