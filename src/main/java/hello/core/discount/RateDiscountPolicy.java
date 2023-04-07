package hello.core.discount;

// DiscountPolicy의 정률 금액 할인 구현체.


import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")  // 조회 대상 빈이 2개일때 해결법 2: @Qualifier
//@Primary  // 조회 대상 빈이 2개일때 해결법 3: @Primary를 넣으면 우선 순위로 먼저 주입이 됨.
// 예를 들어 메인 DB와 보조 DB가 있으면 메인 DB에 @Primary를 넣어 사용하고, 보조 DB를 사용할때 만 1번 or 2번 방법을 사용하면 좋음.
// Qulifier와 Primary가 동시에 작동하면 수동으로 작성해준 Qulifier가 우선권이 높다.
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    // VIP 고정 할인 10%
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
