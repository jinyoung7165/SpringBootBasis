package basis01.basis01spring.repository;

import basis01.basis01spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //저장소에 멤버 저장
    Optional<Member> findById(Long id); //null일 때 Optional로 감쌈
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
