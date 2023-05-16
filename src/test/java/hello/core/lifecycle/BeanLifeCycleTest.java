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

    /* 생명주기 콜백 지원 방법 1. 인터페이스
    생성자 호출 단계에선 url = null. 생성자 호출이 끝나고 의존관계 주입이 일어난 후
    afterPropertiesSet이 호출되어 connect()메서드 실행. 이떄는 url 값 존재.
    그 후 컨테이너가 내려갈 때 destroy이 호출되어 disconnect() 실행. 스프링 종료.

    단점
    - 스프링 전용 인터페이스를 사용하여 코드가 스프링 전용 인터페이스에 의존한다.
    - 초기화, 소멸 메서드의 이름을 변경할 수 없다.
    - 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

    -> 스프링 초창기의 방법으로 더 나은 방법들의 등장으로 정요즘은 잘 사용하지 않음.
     */

    @Configuration
    static class LifeCycleConfig {

        /* 생명주기 콜백 지원 방법 2. 설정정보에 초기화, 종료 메서드 지정
        아래와 같이 설정정보에 초기화 메서드를 initMethod로, 종료 메서드를 destroyMethod로 지정해줌.

        특징
        - 메서드 이름이 자유롭다.
        - 스프링 빈이 스프링 코드에 의존하지 않는다.
        - 코드를 고칠 수 없는 외부 라이브러리에도 초기화, 종료 메서드를 적용할 수 있다.

        destroyMethod는 기본값이 (inferred)인데, 이 것은 'close', 'shutdown'라는 이름의 메서드를 찾아 자동으로 호출해준다.
        직접 스프링 빈으로 등록하면 destroyMethod에 종료 메서드를 따로 적어주지 않아도 대부분 잘 동작한다.
         */
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
