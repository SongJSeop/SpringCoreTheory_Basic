package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A 사용자가 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A가 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price); // 10000원이 기대되지만 20000원이 나옴.
        // 다른 쓰레드에서 statefulService2로 주문한 20000원이 statefulService1의 price 변수에도 적용. 싱글톤 사용 주의점.

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    // 테스트용 Config
    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}