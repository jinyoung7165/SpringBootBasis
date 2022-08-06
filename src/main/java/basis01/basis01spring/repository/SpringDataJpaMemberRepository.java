package basis01.basis01spring.repository;

import basis01.basis01spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository와 MemberRepository interface를 상속받는 interface
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //<type, id>
     @Override
     Optional<Member> findByName(String name); //jpaRepository에서 기본 제공
}
