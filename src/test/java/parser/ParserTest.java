package parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParserTest {


    @Test
    @DisplayName("파일에서 가져온 메뉴 파싱 성공 테스트")
    void StringMenuParsingSuccessTest() {
        // given
        String menu = "치킨버거, 7000, 15, 치킨으로 만든 햄버거, 햄버거";
        String[] result = {"치킨버거","7000","15","치킨으로 만든 햄버거","햄버거"};
        // when
        String[] parsedMenu = Parser.parseToMenu(menu);
        // then
        assertThat(parsedMenu).isEqualTo(result);
    }

}