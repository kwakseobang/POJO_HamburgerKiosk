package file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileServiceTest {

    private final String FILE_PATH = "src/main/resources/products.md";
    private FileService fileService;

    @BeforeEach
    void setUo() {
        fileService = new FileService();
    }

    @Test
    @DisplayName("파일 읽어오기 테스트")
    void readFile() {
        // given
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // when
        fileService.read();

        // 원래 콘솔 출력으로 복원
        System.setOut(originalOut);
        String output = outputStream.toString().trim();

        assertThat(output).isNotEmpty();

        System.out.println(output);
    }
    @Test
    @DisplayName("파일 load test")
    void loadFile() {
        // given
        File file = new File(FILE_PATH);
        // when
        assertThat(file).exists().isFile().canRead();
    }

}