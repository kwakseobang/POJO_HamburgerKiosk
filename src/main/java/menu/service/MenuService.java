package menu.service;

import file.service.FileService;
import java.util.List;
import menu.domain.MenuList;
import menu.dto.MenuCreateDto;
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
            MenuCreateDto menuCreateDto = new MenuCreateDto(parsedMenuItem);
            menuRepository.save(menuCreateDto);
        }
    }

    public MenuList readMenuList() {
        return menuRepository.getMenuList();
    }

}