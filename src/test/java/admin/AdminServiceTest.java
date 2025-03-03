package admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import admin.service.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;
    @Mock
    private AdminRepository adminRepository;

    @Test
    @DisplayName("관리자 생성 성공 테스트")
    void createAdminSuccessTest() {
        // given
        Admin admin = new Admin("Kwak", 10000);
        given(adminRepository.isExistName("Kwak")).willReturn(false);

        // when
        Admin createdAdmin = adminService.createAdmin(admin);

        // then
        assertThat(createdAdmin).isNotNull();
        verify(adminRepository).create(createdAdmin);  // adminRepository.create() 호출 확인
    }

    @Test
    @DisplayName("존재하는 관리자 이름으로 로그인 시 테스트 통과")
    void loginAdminSuccessTest() {
        // given
        Admin admin = new Admin("Kwak", 10000);
        given(adminRepository.findByAdmin("Kwak")).willReturn(admin);

        // when
        Admin result = adminService.login("Kwak");

        // then
        assertThat(result).isNotNull().isEqualTo(admin);
    }

    @Test
    @DisplayName("존재하지 않는 관리자로 로그인 시 예외를 던진다.")
    void loginAdminFailTest() {
        // given
        String name = "Kwak";
        Admin admin = new Admin(name, 10000);
        given(adminRepository.findByAdmin(name)).willReturn(null);

        // when & then
        assertThatThrownBy(() -> adminService.login(name))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), name));
    }

}