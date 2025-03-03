package customer;

import static io.Input.input;

import io.OutPut;

public class CustomerInput {

    private final CustomerInputParser customerInputParser;

    public CustomerInput() {
        this.customerInputParser = new CustomerInputParser();
    }

    public Customer inputInfo() {
        OutPut.promptMemberInfo(); // ex) 생성하실 회원의 이름과 보유금액을 입력해주세요.
        String input = input();
        return customerInputParser.parseToInfo(input);
    }

    public long inputUniqueNumber() {
        OutPut.promptMemberUniqueNumber(); // ex) 접속하시는 회원의 번호를 입력해주세요.
        return Long.parseLong(input());
    }
}