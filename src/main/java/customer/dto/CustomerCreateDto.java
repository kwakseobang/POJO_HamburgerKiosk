package customer.dto;

import customer.domain.Customer;

public record CustomerCreateDto(long id, long amount) {

    public Customer to() {
        return new Customer(id, amount);
    }

}