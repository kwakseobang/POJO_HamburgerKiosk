package admin;

import static io.Input.input;

import io.OutPut;

public class AdminInput {

    private final InputParser inputParser;

    public AdminInput() {
        this.inputParser = new InputParser();
    }

    public Admin inputInfo() {
        OutPut.promptAdminInfo(); // ex) 생성하실 관리자의 이름과 보유금액을 입력해주세요.
        String input = input();
        return inputParser.parseToAdminInfo(input);
    }

    public String inputName() {
        OutPut.promptAdminName(); // ex) 접속하시는 관리자의 이름을 입력해주세요.
        return input();
    }

}