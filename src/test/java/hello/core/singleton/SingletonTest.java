package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 서로 다르다. 클라이언트들이 동시에 요청을 하면 그 때마다 새로운 객체를 생성한다는 뜻. 효율적이지 않음(메모리 낭비).
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

        // 그러므로 해당 객체가 그때마다 아닌 딱 하나만 생성되고 그 객체가 공유되어 모두 처리하면 효율적임. -> 싱글톤 패턴
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 둘 모두 같은 객체가 생성됨. 효율적인 싱글톤 패턴.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        // isSameAs : 인스턴스 비교
        // isEqualTo : 자바 Equal 메소드 같은 것
        assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        // 스프링 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 스프링 컨테이너를 이용하여 memberService1, memberService2를 불러옴.
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 같은 참조값을 가짐.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 테스트 통과.
        assertThat(memberService1).isSameAs(memberService2);

        // 이렇게 스프링 컨테이너를 이용하면 위의 싱글톤 패턴의 장점(객체 1개로 공유)은 이용하고 단점들(지저분한 코드 등)을 극복할 수 있다.
    }
}
