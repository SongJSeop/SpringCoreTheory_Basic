package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // AutoAppConfig에는 입력된 코드가 아무것도 없지만 자동으로 연결이 되어 테스트가 통과되는 모습.
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        OrderService orderService = ac.getBean(OrderService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
    }
}
