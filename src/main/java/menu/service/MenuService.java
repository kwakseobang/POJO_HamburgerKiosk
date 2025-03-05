package menu.service;

import file.service.FileService;
import java.util.List;
import menu.entity.Menu;
import menu.repository.MenuRepository;
import parser.Parser;

public class MenuService {

    private final FileService fileService;
    private final MenuRepository menuRepository;

    public MenuService(FileService fileService, MenuRepository menuRepository) {
        this.fileService = fileService;
        this.menuRepository = menuRepository;
    }

    public void createMenuList() {
        List<String> loadedFile = loadFile();
        for (String line : loadedFile) { // TODO: 변수명 수정하자 line
            String[] parsedMenuItem = Parser.parseToMenu(line);
            Menu menu = new Menu(parsedMenuItem);
            menuRepository.save(menu);
        }
    }

    public List<Menu> readMenuList() {
        return menuRepository.readMenuList();
    }

    private List<String> loadFile() {
        return fileService.load();
    }

}