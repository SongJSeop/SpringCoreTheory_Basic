package hello.core.discount;

// 할인 정책 역할의 Interface.

import hello.core.member.Member;

public interface DiscountPolicy {

    // 할인 대상 금액. VIP라면 1000원 할인.
    int discount(Member member, int price);
}
