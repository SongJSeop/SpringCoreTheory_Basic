package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
//        // 스캔을 시작할 위치를 지정. 만약 hello.core.member라고 하면 member 패키지 안의 컴포넌트들만 스캔의 대상이 됨.
//        basePackages = "hello.core",
//        // basePackageClasses로 지정된 것의 패키지를 시작 위치로 지정 가능. AutoAppConfig.class의 패키지는 맨 위의 hello.core이다.
//        basePackageClasses = AutoAppConfig.class,
        // 만약 위의 두 개를 다 지정하지 않으면 디폴트 값은 @ComponentScan이 달린(AutoAppConfig)의 패키지(hello.core)를 스캔 시작 위치로.
        // 권장 방법은 패키지 위치 지정 없이 설정 정보(AppConfig) 클래스 위치를 프로젝트 최상단에 두는 것.

        /* 컴포넌트 스캔 대상
            @Component
            @Controller: 스프링 MVC 컨트롤러로 인식.
            @Service: 특별한 처리는 하지 않음. 개발자들이 핵심 비즈니스 로직이 여기 있겠구나 라고 인식시켜주는 역할.
            @Repository: 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해주는 역할.
            @Configuration: 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리.  */

        // 원래의 AppConfig를 스캔하는 것을 방지하기 위해 Configuration 클래스들은 스캔에서 제외.
        // 실무에서는 이렇게 제외하는 경우는 거의 없지만 기본 예제 코드를 유지하기 위해 제외해주는 것.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 아래처럼 수동으로 이름을 지정한 빈과 자동으로 지정된 빈(memoryMemberRepository)가 겹치면 스프링 어플리케이션 실행이 되지 않는다.
    // 예전에는 이 경우 수동으로 이름을 지정한 빈이 우선권을 가지면서 실행이 되었었다.(수동 빈이 자동 빈을 오버라이딩 해버린다.)
    // 예전처럼 오버라이딩 하고 싶다면 application.properties에 spring.main.allow-bean-definition-overriding=tr 옵션을 추가한다.
//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}