//package hello.core.order;
//
//import hello.core.discount.DiscountPolicy;
//import hello.core.member.Member;
//import hello.core.member.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//// 일반 메서드 주입 필기용 클래스
//@Component
//public class OrderServiceImplInit implements OrderService {
//
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    /* 의존관계 주입 4. 일반 메서드 주입
//        일반 메서드를 통해서 주입. 한번에 여러 필드를 주입 받을 수 있다. 일반적으로 잘 사용하진 않음.*/
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//
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
