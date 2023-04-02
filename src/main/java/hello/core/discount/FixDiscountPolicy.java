package hello.core.discount;

// DiscountPolicy의 고정 금액 할인 구현체.

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

// 이렇게 FixDiscountPolicy, RateDiscountPolicy 모두 @Component를 붙이면 DiscountPolicy를 호출할 때 조회되는 빈이 2개라 에러 발생.
@Component
public class FixDiscountPolicy implements DiscountPolicy {

    // VIP 고정 할인 금액 1000원
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
