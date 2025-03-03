package customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import admin.AdminErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    @DisplayName("회원 생성 성공 테스트")
    void createCustomerSuccessTest() {
        // given
        Customer customer = new Customer(1L, 10000);
        given(customerRepository.isExistId(1L)).willReturn(false);

        // when
        Customer createdCustomer = customerService.createCustomer(customer);

        // then
        assertThat(createdCustomer).isNotNull();
        verify(customerRepository).create(createdCustomer);  // adminRepository.create() 호출 확인
    }

    @Test
    @DisplayName("존재하는 회원 번호로 로그인 시 테스트 통과")
    void loginCustomerSuccessTest() {
        // given
        long id = 1L;
        Customer customer = new Customer(id, 10000);
        given(customerRepository.findByCustomer(id)).willReturn(customer);

        // when
        Customer result = customerService.login(id);

        // then
        assertThat(result).isNotNull().isEqualTo(customer);
    }

    @Test
    @DisplayName("존재하지 않는 회원 번호로 로그인 시 예외를 던진다.")
    void loginAdminFailTest() {
        // given
        long id = 1L;
        Customer customer = new Customer(id, 10000);
        given(customerRepository.findByCustomer(id)).willReturn(null);

        // when & then
        assertThatThrownBy(() -> customerService.login(id))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(AdminErrorMessage.NOT_EXIST_NAME.getMessage(), id));
    }

}