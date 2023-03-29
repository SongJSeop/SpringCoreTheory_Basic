//package hello.core.order;
//
//import hello.core.discount.DiscountPolicy;
//import hello.core.member.Member;
//import hello.core.member.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//// 수정자주입 필기용 클래스
//@Component
//public class OrderServiceImplSetter implements OrderService {
//
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    /* 의존관계 주입 2. 수정자 주입(setter 주입)
//        final과 같이 쓰지 않음. @Autowired를 사용하면 OrderServiceImplSetter 호출 시 set 메소드를 이용하지 않아도 자동으로 주입해줌.
//        @Autowired를 사용하지 않으면 set~~를 해줘야 주입이 됨.
//        수정자주입은 선택, 변경 가능성이 있는 의존관계에 사용.(만약 memberRepository가 스프링빈에 등록되지 않아도 사용 가능함.)
//
//        스프링 라이프 싸이클
//        1. 스프링빈에 OrderServiceImplSetter를 등록. -> 생성자를 통한 자동 주입
//        2. 자동으로 의존관계 주입 -> 수정자 자동 주입
//        1번, 2번 둘 다 동일한 memberRepository, discountPolicy가 주입됨.(싱글톤)*/
//    @Autowired//(required = false)를 붙이면 필수가 아니라는 의미(주입할 대상이 없어도 동작)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    @Autowired
//    public OrderServiceImplSetter(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//
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
