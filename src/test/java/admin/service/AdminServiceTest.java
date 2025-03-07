package admin.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import admin.domain.Admin;
import admin.repository.AdminRepository;
import admin.response.AdminErrorMessage;
import file.service.FileService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import menu.service.MenuService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    private AdminService adminService;
    private AdminRepository adminRepository;
    private MenuService menuService;
    private final InputStream originalSystemInStream = System.in;

    @BeforeEach
    void setUp() {
        menuService = new MenuService(new FileService());
        adminService = new AdminService(menuService);
        adminRepository = new AdminRepository();
    }

    @AfterEach
    void restoreSystemInStreamStream() {
        System.setIn(originalSystemInStream);
    }
    // FIXME: adminRepository.create() 호출 시 오류 남. 하지만 정상적으로 작동 함. 테스트 코드 문제 인 듯
    @Test
    @DisplayName("관리자 생성 성공 테스트.")
    void createAdminSuccessTest() {
        String input = "Kwak, 10000";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Admin admin = new Admin("Kwak", 10000);
        given(adminRepository.isExistByName("Kwak")).willReturn(false);
        // when
        adminService.create(admin);

        verify(adminRepository).create(admin);
    }

    @Test
    @DisplayName("관리자 생성 실패 테스트.")
    void createAdminFailedTest() {
        // given
        String input = "Kwak, 10000";
        String name = "Kwak";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Admin admin = new Admin(name, 10000);
        given(adminRepository.isExistByName(name)).willReturn(true);
        // when & given
        assertThatThrownBy(() -> adminService.create(admin))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(AdminErrorMessage.DUPLICATION_ADMIN.getMessage(), name));

    }

    @Test
    @DisplayName("관리자 로그인 성공 테스트")
    void loginAdminSuccessTest() {
        // given
        String name = "Kwak";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        Admin admin = new Admin(name, 10000);
        given(adminRepository.findByAdmin(name)).willReturn(Optional.of(admin));
        // when
        String result = adminService.login(name);
        // then
        assertThat(result).isNotNull().isEqualTo(admin);
    }

    @Test
    @DisplayName("관리자 로그인 실패 테스트")
    void loginAdminFailTest() {
        // given
        String name = "Kwak";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        given(adminRepository.findByAdmin(name)).willReturn(Optional.empty());
        // when & then
        assertThatThrownBy(() -> adminService.login(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(AdminErrorMessage.NOT_EXIST_ADMIN.getMessage(), name));
    }

}