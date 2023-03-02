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

@Configuration
public class AppConfig {

    // 역할과 구현 나누기
    // 회원 서비스는 MemberServiceImpl을 사용.
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 회원 저장소는 MomoryMemberRepository를 사용.
    @Bean
    public static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 주문 서비스는 OrderServiceImpl을 사용.
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책은 RateDiscountPolicy를 사용.
    @Bean
    public static DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
