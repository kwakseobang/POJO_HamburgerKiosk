package admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputParserTest {

    private InputParser inputParser;

    @BeforeEach
    void setUp() {
        inputParser = new InputParser();
    }

    @Test
    @DisplayName("정상적으로 입력된 정보 파싱 테스트")
    void parseToAdminInfoTest() {
        // given
        String input = "John, 1000";
        //when
        Admin admin = inputParser.parseToAdminInfo(input);
        //then
        assertThat(admin.getName()).isEqualTo("John");
        assertThat(admin.getAmount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("잘못된 구분자 파싱 테스트")
    void invalidParserTest() {
        // given
        String input = "John: 1000";
        // when & then
        assertThatThrownBy(() -> inputParser.parseToAdminInfo(input))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(AdminErrorMessage.INVALID_SEPARATOR.getMessage());
    }

}