package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // Controllor -> Service -> Repository 로 의존 관계가 되어야한다
public class MemberController {

    private final MemberService memberService;

    @Autowired // 의존성 주입을 할 때 사용하는 Annotation으로 의존 객체의 타입에 해당하는 bean을 찾아 주입하는 역할
    public MemberController(MemberService memberService) { // 생성자 주입 방법
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // <form action> 을 통해 input값을 가져온다.
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 리다이렉트 기능으로 'redirect:경로' 입력
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
