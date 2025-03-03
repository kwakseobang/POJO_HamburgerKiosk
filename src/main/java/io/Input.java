package io;

import admin.AdminErrorMessage;
import java.util.Scanner;

public abstract class Input {

    private static final Scanner sc = new Scanner(System.in);

    public String getValidatedInput() {
        String input = sc.nextLine();
        validateInput(input);
        return input;
    }

    protected void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.INVALID_INPUT.getMessage(), input)
            );
        }
    }

}