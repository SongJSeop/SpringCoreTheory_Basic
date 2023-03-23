package hello.core;

// DIP, OCP를 지키기 위해(추상, 즉 인터페이스에만 서로 의존하게 하기 위해) 구현체를 대신 주입해주는 Config 파일.

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 여기서 @Configuration을 붙이지 않고 @Bean만 사용하여도 스프링 빈으로 등록은 되지만 싱글톤을 보장해주지는 않는다.
@Configuration
public class AppConfig {

    // 역할과 구현 나누기
    // 회원 서비스는 MemberServiceImpl을 사용.

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 이렇게 각각 두 번 호출되어 싱글톤이 깨지는 것 처럼 보인다.
    // 그러나 모두 같은 memberRepository를 호출한다.

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 회원 저장소는 MomoryMemberRepository를 사용.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 주문 서비스는 OrderServiceImpl을 사용.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책은 RateDiscountPolicy를 사용.
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
