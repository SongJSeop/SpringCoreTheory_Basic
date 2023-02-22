package hello.core.member;

// 회원 저장소 역할의 Interface.

public interface MemberRepository {

    // 회원가입하는 회원을 저장하는 메소드
    void save(Member member);

    // 아이디로 회원을 조회하는 메소드
    Member findById(Long memberId);
}
