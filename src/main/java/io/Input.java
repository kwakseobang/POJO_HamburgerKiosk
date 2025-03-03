package io;

import admin.AdminErrorMessage;
import java.util.Scanner;

public class Input {

    private static final Scanner sc = new Scanner(System.in);

    private Input() {
    }

    public static String input() {
        String input = sc.nextLine();
        validateInput(input);
        return input;
    }

    private static void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(
                String.format(AdminErrorMessage.INVALID_INPUT.getMessage(), input)
            );
        }
    }

}