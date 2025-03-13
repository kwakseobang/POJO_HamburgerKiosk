package menu.service;

import file.service.FileService;
import java.util.List;
import menu.domain.Menu;
import menu.domain.MenuList;
import menu.repository.MenuRepository;
import parser.Parser;

public class MenuService {

    private final FileService fileService;
    private final MenuRepository menuRepository;

    public MenuService(FileService fileService) {
        this.fileService = fileService;
        this.menuRepository = new MenuRepository();
    }

    public void createMenuList() {
        List<String> loadedFile = fileService.load();
        for (String line : loadedFile) {
            String[] parsedMenuItem = Parser.parseToMenu(line);
            menuRepository.save(Menu.createMenu(parsedMenuItem));
        }
    }

    public MenuList readMenuList() {
        return menuRepository.getMenuList();
    }

}