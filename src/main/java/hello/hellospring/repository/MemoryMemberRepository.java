package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// @Repository // controller와 service와의 의존 관계, 다른 방법의 의존관계도 있어서 주석처리, Bean으로 본다
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 아이디와 멤버 key와 value로 설정
    private static long sequence = 0L;

    @Override
    // 멤버 저장하여 생성
    public Member save(Member member) {
        member.setId(++sequence); // 멤버의 아이디 값 1씩 증가하여 생성
        store.put(member.getId(), member); // 아이디와 멤버 저장
        return member;
    }

    @Override
    // 아이디로 찾기
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // get()은 Map의 key값을 넣어 value 호출. 그 값을 optional 인스턴스 리턴
    }

    @Override
    // 이름으로 찾기
    public Optional<Member> findByName(String name) {
        return store.values().stream() // Map의 value값들을 모아 스트림생성
                .filter(member -> member.getName().equals(name)) // 중간 연산자로 이름 찾아 일치하는지 찾아본다
                .findAny(); // 최종연산자로 있으면 반환 없으면 null
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // value의 모든 값들을 리스트에 넣어 반환
    }

    public void clearStore() {
        store.clear(); // Map 비우기
    }
}
