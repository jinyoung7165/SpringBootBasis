package basis01.basis01spring.service;

import basis01.basis01spring.domain.Member;
import basis01.basis01spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    //MemberService에서 생성한 MemoryMemberRepository랑 아래의 new로 생성한 MemoryMemberRepository다름
    // -> 내부 store다를 수 있음 (현재는 static으로 store가 선언돼있기 때문에 같음)
    // -> MemberService 생성자를 통해 외부에서 같은 memberRepository를 넣어줌!!!!!!!!!!!! DI

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() { //각 테스트 동작 전에 실행 -> 서비스에게 이미 생성된 레포지토리 넘겨줌(DI)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() { //각 테스트마다 DB비워줌
        memberRepository.clearStore();
    }

    @Test
    void 중복_회원_예외() {
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //(expected exception, logic) logic을 실행할 때 exception이 발생해야함
        
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}