package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

// @Service // controller와 repository와의 의존 관계, 다른 방법의 의존관계도 있어서 주석처리, bean으로 본다
public class MemberService {
    /*
        private final MemberRepository memberRepository = new MemoryMemberRepository();
        
        MemberServiceTest 클래스의 new MemoryMemberRepository()와 엄연히 다른 인스턴스이다.
        MemoryMemberRepository 클래스의 Map이 static 이여서 문제 없지만 만약 없다면 서로 다른 인스턴스에서 접근하기 때문에 DB 접근시 문제가 생긴다.
        그래서 MemberService와 MemberServiceTest가 같은 인스턴스로 접근 가능하게 하는 것이 좋다.
        그 표현 방법이 DI(의존성 주입) 방법 
    */
    private final MemberRepository memberRepository;

    // @Autowired // 다른 방법의 의존관계도 있어서 주석처리
    public MemberService(MemberRepository memberRepository) { // 생성자로 MemberRepository에 접근
        this.memberRepository = memberRepository; // DI 구성 (의존성 주입)
    }

    // 회원가입
    public Long join(Member member) {

        validateDuplicateMember(member); // 같은 이름이 있는 회원x

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); // 같은 이름이 있으면 예외처리
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
