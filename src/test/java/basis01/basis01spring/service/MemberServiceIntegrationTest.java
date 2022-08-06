package basis01.basis01spring.service;

import basis01.basis01spring.domain.Member;
import basis01.basis01spring.repository.MemberRepository;
import basis01.basis01spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //spring container 실제로 띄워서 test(웹 띄울 필요없음)
@Transactional //Rollback -> test마다 DB에 넣은 data사라짐
class MemberServiceIntegrationTest {
    //MemberService에서 생성한 MemoryMemberRepository랑 아래의 new로 생성한 MemoryMemberRepository다름
    // -> 내부 store다를 수 있음 (현재는 static으로 store가 선언돼있기 때문에 같음)
    // -> MemberService 생성자를 통해 외부에서 같은 memberRepository를 넣어줌!!!!!!!!!!!! DI

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    public void 회원가입() throws Exception {
      Member member = new Member();
      member.setName("hello");
      Long saveId = memberService.join(member);
      Member findMember = memberRepository.findById(saveId).get();
      assertEquals(member.getName(), findMember.getName());
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

}