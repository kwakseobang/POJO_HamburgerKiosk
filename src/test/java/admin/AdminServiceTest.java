package admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import admin.service.AdminService;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminService adminService;
    @Mock
    private AdminRepository adminRepository;
    private final InputStream originalSystemInStream = System.in;

    @AfterEach
    void restoreSystemInStreamStream() {
        System.setIn(originalSystemInStream);
    }

    @Test
    @DisplayName("관리자 생성 성공 테스트")
    void createAdminSuccessTest() {
        // given
        String input = "Kwak, 10000";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Admin admin = new Admin("Kwak", 10000);
        given(adminRepository.isExistName("Kwak")).willReturn(false);

        // when
        Admin createdAdmin = adminService.createAdmin();

        // then
        assertThat(createdAdmin).isNotNull();
        verify(adminRepository).create(createdAdmin);  // adminRepository.create() 호출 확인
    }

    @Test
    @DisplayName("존재하는 관리자 이름으로 로그인 시 테스트 통과")
    void loginAdminSuccessTest() {
        // given
        String input = "Kwak";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Admin admin = new Admin("Kwak", 10000);
        given(adminRepository.findByAdmin("Kwak")).willReturn(admin);

        // when
        Admin result = adminService.login();

        // then
        assertThat(result).isNotNull().isEqualTo(admin);
    }

    @Test
    @DisplayName("존재하지 않는 관리자로 로그인 시 예외를 던진다.")
    void loginAdminFailTest() {
        // given
        String name = "Kwak";
        System.setIn(new ByteArrayInputStream(name.getBytes()));
        given(adminRepository.findByAdmin(name)).willReturn(null);

        // when & then
        assertThatThrownBy(() -> adminService.login())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), name));
    }

}