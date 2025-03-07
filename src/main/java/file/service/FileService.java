package file.service;

import file.response.FileErrorMessage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import menu.domain.Menu;

public class FileService {

    private final String FILE_PATH = "src/main/resources/products.md";

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
            br.close();
            return fileLines;
        } catch (IOException ex) {
            throw new RuntimeException(FileErrorMessage.FAILED_READ_FILE.getMessage());
        }
    }

    public void saveMenusToFile(List<Menu> menuList) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Menu menu : menuList) {
                String line = menuFormatting(menu);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(FileErrorMessage.FAILED_WRITE_FILE.getMessage());
        }
    }

    private String menuFormatting(Menu menu) {
        return String.format("%s,%d,%s,%s,%s",
            menu.getName(),
            menu.getPrice(),
            menu.getQuantity(),
            menu.getDescription(),
            menu.getCategory()
        );
    }

    private void validateFile(File file) {
        if (!file.exists()) {
            throw new RuntimeException(FileErrorMessage.EMPTY_FILE.getMessage());
        }
    }

}