package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /*
        MemberService memberService = new MemberService();
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();

        MemberService 클래스의 new MemoryMemberRepository()와는 엄연히 다른 인스턴스이다.
        MemoryMemberRepository 클래스의 Map이 static 이여서 문제 없지만 만약 없다면 서로 다른 인스턴스에서 접근하기 때문에 DB 접근시 문제가 생긴다.
        그래서 MemberService와 MemberServiceTest가 같은 인스턴스로 접근 가능하게 하는 것이 좋다.
        그 표현 방법이 DI(의존성 주입) 방법
    */
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 테스트하기 전마다 여기가 실행이 된다
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // 인스턴스를 생성자에 삽입
    }

    @AfterEach // 테스트하고 끝날때마다 여기가 실행이 된다.
    public void afterEach() {
        memberRepository.clearStore(); // 한번에 테스트시 Map의 key값이 중복이 되지않게 지움
    }

    @Test
        // 테스트에서는 메소드 한글명 가능
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 아래의 try-catch를 표현
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // exception의 메시지를 확인하여 내가 의도한 메시지와 같은지 테스트

//        try {
//            memberService.join(member2);
//            fail(); // 무조건 실패로 AssertionError가 발생하고 다음 라인부터 실행되지 않는다.
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}