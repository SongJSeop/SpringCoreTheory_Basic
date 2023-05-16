package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }
    /*
    NetworkClient 클래스의 생성자에는 url 정보가 없이 connect()가 호출된다.
    객체 생성 후 외부에서 수정자(seturl) 주입을 통해야만 url이 존재하게 된다.

    스프링 빈은 간단하게 ( 객체 생성 -> 의존관계 주입 ) 와 같은 라이프사이클을 가진다.
    객체 생성 후 의존관계 주입이 끝난 다음에야 필요한 데이터를 사용할 수 있는 준비가 완료된다.
    그러므로 초기화 작업은 의존관계 주입이 모두 완료되고 난 다음에 호출해야 한다.

    이를 위해 의존관계 주입이 완료된 시점을 알아야 하는데, 스프링은 의존관계 주입이 완료되면 스프링 빈에게
    콜백 메서드를 통해 초기화 시점을 개발자에게 알려주는 다양한 기느을 제공한다.
    또한 스프링 컨테이너가 종료되기 직전에 소멸 콜백을 준다.

    스프링 빈의 이벤트 라이프 사이클
    스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료

    생성자: 필수 정보(파라미터)를 받고, 메모리를 할당하여 객체를 생성하는 책임
    초기화: 이렇게 생성된 값들을 활용하여 외부 커넥션을 연결하는 등 무거운 동작 수행
    -> 생성자 안에서 무거운 초기화 작업을 함께 하는 것 보다는 객체 생성 부분과 초기화 부분을 명확히 나누는 것이 좋음.
       물론 초기화 작업이 간단하다면 생성자에서 한 번에 다 처리하는게 나을 수도 있다.
     */

    @Configuration
    static class LifeCycleConfig {

        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
