package hello.core.member;

// 회원 관련 서비스를 제공하는 역할의 Interface.

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);
}
