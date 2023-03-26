package hello.core.member;

// MemberService의 구현체.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component("memberService") 이런 식으로 컴포넌트 네임을 정해줄 수 있다.
// 다만 위와 같이 수동으로 설정한 두 개의 중복된 이름이 있다면 스캔 시 컴파일 오류가 발생한다.
@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class)가 자동으로 쓰이는 것 처럼 주입해줌.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용 메소드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
