package basis01.basis01spring.repository;

import basis01.basis01spring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{
    //dependencies에서 jpa설치->스프링부트가 자동으로 EntityManager생성
    private final EntityManager em; 
    public JpaMemberRepository(EntityManager em) { //이미 있는 entitymanger를 주입    
        this.em = em;    
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //영구저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); //type과 key로 조회
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny(); //하나만 리턴
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
