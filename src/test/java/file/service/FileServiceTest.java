package file.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import file.response.FileErrorMessage;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    @Test
    @DisplayName("파일을 성공적으로 읽는다.")
    void fileReadSuccessTest() {
        // when
        List<String> lines = fileService.load();
        // then
        assertThat(lines).isNotEmpty();
        for(String line:lines) {
            System.out.println(line);
        }
    }

    @Test
    @DisplayName("파일이 없을 경우 예외를 던진다.")
    void fileReadFailedTest() {
        // given
        FileService invaliedFileService = new FileService() {
            @Override
            public List<String> load() {
                throw new RuntimeException(FileErrorMessage.EMPTY_FILE.getMessage());
            }
        };
        // when & then
        assertThatThrownBy(invaliedFileService::load)
            .isInstanceOf(RuntimeException.class)
            .hasMessage(FileErrorMessage.EMPTY_FILE.getMessage());
    }

}