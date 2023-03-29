package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 수정자주입 필기용 클래스
//@Component
//public class OrderServiceImplField implements OrderService {

    /* 의존관계 주입 3. 필드 주입
        의존관계를 필드에 바로 넣어줌. 코드는 간결하지만 외부에서 변경이 불가능해 테스트가 힘들다. 그래서 추천되는 방식은 아님.
        애플리케이션과 관계 없는 테스트코드나 설정 목적으로하는 @Configuration 같은 곳에서만 특별한 용도로 사용하도록 하자.*/
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

//    @Override
//    public Order createOrder(Long memberId, String itemName, int itemPrice) {
//        Member member = memberRepository.findById(memberId);
//        int discountPrice = discountPolicy.discount(member, itemPrice);
//
//        return new Order(memberId, itemName, itemPrice, discountPrice);
//    }
//
//    // 테스트용 메소드
//    public MemberRepository getMemberRepository() {
//        return memberRepository;
//    }
//}
