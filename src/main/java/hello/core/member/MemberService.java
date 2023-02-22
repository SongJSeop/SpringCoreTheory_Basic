package hello.core.member;

// 회원 관련 서비스를 제공하는 역할의 Interface.

public interface MemberService {

    // 회원가입 메소드
    void join(Member member);

    // 아이디로 회원을 조회하는 메소드
    Member findMember(Long memberId);
}
