package basis01.basis01spring.repository.controller;

import basis01.basis01spring.domain.Member;
import basis01.basis01spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //spring container가 뜰 때 해당 controller 객체를 만들어서 관리하자
public class MemberController {

    private final MemberService memberService;

    @Autowired //MemberController가 생성될 때 등록돼 있는 MemberService객체를 controller에 넣어줌 (DI)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //form에서 post누르면
    public String create(MemberForm form) { //form의 Setter로 form의 name이 지정됨
        Member member = new Member();
        member.setName(form.getName()); //Getter로 name을 꺼내고 member의 name을 지정

        memberService.join(member); //가입함

        return "redirect:/"; //루트로 가게함
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    
}
