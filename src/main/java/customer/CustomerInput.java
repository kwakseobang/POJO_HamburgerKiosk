package customer;

import io.Input;
import io.OutPut;

public class CustomerInput extends Input {

    private final CustomerInputParser customerInputParser;

    public CustomerInput() {
        this.customerInputParser = new CustomerInputParser();
    }

    public Customer inputInfo() {
        OutPut.promptMemberInfo(); // ex) 생성하실 회원의 이름과 보유금액을 입력해주세요.
        String input = getValidatedInput();
        return customerInputParser.parseToInfo(input);
    }

    public String inputUniqueNumber() {
        OutPut.promptMemberUniqueNumber(); // ex) 접속하시는 관리자의 이름을 입력해주세요.
        return getValidatedInput();
    }
}