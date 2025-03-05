package menu.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import file.service.FileService;
import java.util.*;
import menu.entity.Menu;
import menu.repository.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MenuServiceTest {

    @InjectMocks
    MenuService menuService;
    @Mock
    FileService fileService;
    @Mock
    MenuRepository menuRepository;

    @Test
    @DisplayName("파일을 불러와 메뉴리스트에 저장한다.")
    void loadFileAndAddToMenuListSuccessTest() {
        // given
        List<String> mockFileLines = Arrays.asList(
            "치킨버거, 7000, 15, 치킨으로 만든 햄버거, 햄버거",
            "치킨버거세트, 9000, 15, 치킨으로 만든 햄버거 세트, 세트"
        );
        given(fileService.load()).willReturn(mockFileLines);
        // when
        menuService.createMenuList();
        // then - 메뉴 객체가 정확하게 저장되었는지 검증
        ArgumentCaptor<Menu> menuCaptor = ArgumentCaptor.forClass(Menu.class);
        // save() 호출된 객체 캡처
        verify(menuRepository, times(2)).save(menuCaptor.capture());

        // 캡처한 Menu 객체들에 대해 검증
        List<Menu> capturedMenus = menuCaptor.getAllValues();

        assertThat(capturedMenus).hasSize(2);
        assertThat(capturedMenus.get(0).getName()).isEqualTo("치킨버거");
        assertThat(capturedMenus.get(0).getPrice()).isEqualTo(7000);
        assertThat(capturedMenus.get(0).getCategory()).isEqualTo("햄버거");

        assertThat(capturedMenus.get(1).getName()).isEqualTo("치킨버거세트");
        assertThat(capturedMenus.get(1).getPrice()).isEqualTo(9000);
        assertThat(capturedMenus.get(1).getCategory()).isEqualTo("세트");
    }

}