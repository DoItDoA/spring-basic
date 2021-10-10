package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 테스트하고 끝날때마다 여기가 실행이 된다.
    public void afterEach(){
        repository.clearStore(); // 한번에 테스트시 Map의 key값이 중복이 되지않게 지움
    }

    @Test // 기존 run 하던 것을 중단하고 왼쪽에 run버튼 눌러 test 시작
    public void save() {

        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); // findById 반환값이 optional이기 때문에 get()을 이용하여 호출

        // Assertions.assertEquals(member, result); // 서로 비교하여 같으면 아무것도 안뜨는데 다르면 오류가 뜬다.
        assertThat(member).isEqualTo(result); // 위와 같은 기능, import static 이용하여 앞의 Assertions 생략 (이 방법 좀 더 선호)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get(); // 이름으로 찾기

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
