package basis01.basis01spring.service;

import basis01.basis01spring.domain.Member;
import basis01.basis01spring.repository.MemberRepository;
import basis01.basis01spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional //jpa는 데이터 변경이 transaction안에서 실행돼야 함
public class MemberService { //비즈니스 메소드

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) { //MemberService를 생성할 때 MemberRepository를 서비스에 DI
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원x
        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }
    
    private void validateDuplicateMember(Member member) {
        /**
         *         Optional<Member> result = memberRepository.findByName(member.getName());
         *         result.ifPresent(m -> { //Optional안에 값이 있으면
         *             throw new IllegalStateException("이미 존재하는 회원입니다");
         *         });
         *         =>memberRepository.findByName은 어차피 Optional<Member>반환하니까 아래와 같음
         */
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
