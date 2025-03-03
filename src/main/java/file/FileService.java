package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileService {

    private final String FILE_PATH = "src/main/resources/products.md";

    public void read() {
        File file = load();
        String readLine;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((readLine = br.readLine()) != null) {
                System.out.println(readLine);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    private File load() {
        return new File(FILE_PATH);
    }

}