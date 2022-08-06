package basis01.basis01spring;

import basis01.basis01spring.aop.TimeTraceAop;
import basis01.basis01spring.repository.*;
import basis01.basis01spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration //Spring이 뜰 때 Configuration을 읽고 서비스,레포지토리를 Bean으로 등록하면서 DI
public class SpringConfig {

    /*
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }
*/
    private final MemberRepository memberRepository;
    @Autowired //Spring Container에서 MemberRepository찾는데, MemberRepository와 JpaRepository를 상속한 인터페이스 존재
            //-> Spring Data Jpa가 자동으로 구현체 생성 -> Bean에 등록해놓음
    public SpringConfig(MemberRepository memberRepository) { //JpaRepository를 통해 자동으로 구현체 생성
        this.memberRepository = memberRepository;
    }

    /*
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    */
    @Bean
    public MemberService memberService() { //Spring Data JPA 이용 시 memberRepository가 Bean으로 자동 등록됨
        return new MemberService(memberRepository);
    }
/*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    */
    /*
    @Bean
    public MemberRepository memberRepository() { //MemberRepository의 구현체 생성 -> Bean으로 등록
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
    */

}
