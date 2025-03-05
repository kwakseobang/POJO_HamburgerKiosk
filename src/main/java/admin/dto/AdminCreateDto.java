package admin.dto;

import admin.entity.Admin;

public record AdminCreateDto(String name, long amount) {

    public Admin to() {
        return new Admin(name,amount);
    }

}