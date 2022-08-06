package basis01.basis01spring.repository;

import basis01.basis01spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository{ //구현체

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 concurrentHashMap써야함
    private static long sequence = 0L; //0,1,2..key값 생성. 실무에서는 concurrency문제-atomiclong

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 세팅(시스템이 정해줌)
        store.put(member.getId(), member); //store에 넣어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null일 때 Optional 감싸서 보냄
    }

    @Override
    public Optional<Member> findByName(String name) { //null일 때 Optional 감싸서 보냄
        return store.values().stream() //map->collection->stream->각 멤버의 이름이 인수랑 같은지 비교 => 찾으면 바로 리턴
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //멤버들을 ArrayList로 반환
    }

    public void clearStore() {
        store.clear(); //전체를 비움
    }
}
