package admin;

import static admin.AdminErrorMessage.INVALID_INPUT;

import io.OutPut;
import java.util.Scanner;

public class AdminInput {

    private final InputParser inputParser;
    private static Scanner sc = new Scanner(System.in);

    public AdminInput() {
        this.inputParser = new InputParser();
    }

    public Admin inputAdminInfo() {
        OutPut.promptAdminInfo(); // ex) 생성하실 관리자의 이름과 보유금액을 입력해주세요.
        String input = sc.nextLine();
        validateInput(input);
        return inputParser.parseToAdminInfo(input);
    }

    public String inputAdminName() {
        String name = sc.nextLine();
        validateInput(name);
        return name;
    }

    private void validateInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(String.format(INVALID_INPUT.getMessage(), input));
        }
    }

}