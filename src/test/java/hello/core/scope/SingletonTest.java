package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

/* 빈 스코프
빈 스코프: 빈이 존재할 수 있는 범위

종류
- 싱글톤: 기본 스코프, 스프링 컨테이너 시작과 종료까지 유지되는 가장 넓은 범위의 스코프
- 프로토타입: 빈의 생성과 의존관계 주입까지만 컨테이너가 관여하고 더는 관여하지 않는 매우 짧은 범위의 스코프.
           싱글톤은 빈을 조회하면 항상 같은 인스턴스의 스프링 빈을 반환하지만 프로토타입 스코프는 항상 새로운 인스턴스를 생성해서 반환.
           스프링 컨테이너는 프로토타입 빈을 생성, 의존관계 주입, 초기화 까지만 처리. 그 이후는 클라이언트에 빈을 반환되고 책임이 클라이언트에 넘어감.
- 그 외 웹 관련 스코프
    - rquest: 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
    - session: 웹 세션이 생성되고 종료될 때 가지 유지되는 스코프
    - application: 웹의 서블릿 컨텍스트와 같은 범위로 유지되는 스코프
 */

// 싱글톤 스코프
public class SingletonTest {

    // 같은 인스턴스로 반환되고 컨테이너 시작, 종료시 콜백이 됨.
    @Test
    void singletonBeanFine() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
