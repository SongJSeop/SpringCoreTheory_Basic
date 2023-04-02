package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    // 조회 대상 빈이 2개일때 대처 방법 1: 필드 명을 매칭시켜준다. 이러면 fix, rate 중 rateDiscountPolicy를 가져옴.

    /* 의존관계 주입 1. 생성자 주입
       @Component가 있는 OrderServiceImpl을 스캔할때 아래처럼 @Autowired가 있으면(생성자가 1개라면 @Autowired가 없어도 자동 주입 해줌)
       생성자 주입에 필요한 memberRepository, discountPolicy를 스프링 컨테이너에서 찾아서 주입해줌.
       생성자 주입 방법은 호출 시점에 딱 1번만 호출되는 것이 보장되어 불변, 필수인 의존관계에 사용된다.
       불변: 생성자를 통해서 주입이 된 memberRepository, discountPolicy를 더이상 수정할 방법이 없음. setter를 만들지 않는다.
       필수: final로 지정한 memberRepository와 discountPolicy는 무조건 값이 있어야 한다.
            final은 생성자로만 변경할 수 있음. final을 사용하는 것도 이점이 있다. 만약 final 변수의 구현체가 주입이 안되면 컴파일 오류가 나게됨.*/
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
    // 조회 대상 빈이 2개일때 대처 방법 2: @Qualifier("~")로 지정한 빈을 불러올 수 있다.
    // 하지만 @Qulifier는 @Qualifier를 찾는 용도로만 사용하는게 명확하고 좋다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트용 메소드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
