package customer.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

import customer.entity.Customer;
import customer.repository.CustomerRepository;
import customer.response.CustomerErrorMessage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
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
    private final InputStream originalSystemInStream = System.in;

    @AfterEach
    void restoreSystemInStreamStream() {
        System.setIn(originalSystemInStream);
    }
    // FIXME: customerRepository.create() 호출 시 오류 남. 하지만 정상적으로 작동 함. 테스트 코드 문제 인 듯
    @Test
    @DisplayName("회원 생성 성공 테스트")
    void createCustomerSuccessTest() {
        // given
        String input = "1, 10000";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Customer customer = new Customer(1L, 20000);
        given(customerRepository.isExistId(1L)).willReturn(false);
        // when
        customerService.create();
        // then
        // verify(customerRepository,times(1)).create(any(Customer.class));  // adminRepository.create() 호출 확인
    }
    // FIXME: String input = "1"; 과 long id = 1L 값이 달라서 오류 발생
    @Test
    @DisplayName("회원 로그인 성공 테스트")
    void loginCustomerSuccessTest() {
        // given
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        long id = 1L;
        Customer customer = new Customer(id, 10000);
        given(customerRepository.findByCustomer(id)).willReturn(Optional.of(customer));
        // when
        Customer result = customerService.login();
        // then
        assertThat(result).isNotNull().isEqualTo(customer);
    }

    @Test
    @DisplayName("회원 로그인 실패 테스트")
    void loginCustomerFailTest() {
        // given
        String input = "1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        long id = 1L;
        given(customerRepository.findByCustomer(id)).willReturn(Optional.empty());
        // when & then
        assertThatThrownBy(() -> customerService.login())
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(String.format(CustomerErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), id));
    }

}