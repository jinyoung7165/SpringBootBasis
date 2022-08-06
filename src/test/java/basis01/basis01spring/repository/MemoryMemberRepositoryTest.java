package basis01.basis01spring.repository;

import basis01.basis01spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //레포지토리 잘 동작하는지 테스트

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { //각 Test들이 끝날 때마다 실행 (테스트들끼리 의존관계 없어야함)
        repository.clearStore(); //DB에 저장된 테스트 데이터 지움
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member); //저장

        //만든 애랑 저장된 애랑 같은지 검증해야함 -> Optional 반환 시 get()으로 꺼낼 수 있음(주로 TestCode에서)
        Member result = repository.findById(member.getId()).get();
        //Assertions.assertEquals(member, result); //(expected, actual) 다르면 오류남 :junit
        assertThat(member).isEqualTo(result); //Assertions를 static으로 import함

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
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
