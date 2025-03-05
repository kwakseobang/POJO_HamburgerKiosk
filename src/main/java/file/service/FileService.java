package file.service;

import file.response.FileErrorMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileService {

    private final String FILE_PATH = "src/main/resources/products.md";

    public FileService() {
    }

    public List<String> load() {
        File file = new File(FILE_PATH);
        validateFile(file);
        String readLine;
        List<String> fileLines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((readLine = br.readLine()) != null) {
                fileLines.add(readLine);
            }
            return fileLines;
        } catch (IOException ex) {
            throw new RuntimeException(FileErrorMessage.FAILED_READ_FILE.getMessage());
        }
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException(FileErrorMessage.EMPTY_FILE.getMessage());
        }
    }
}