package com.ttd.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("form")
	public String form() {
		return "member/form";
	}
	
	@PostMapping("form")
	public String create(Member member) {
		System.out.println(member);
		memberRepository.save(member);
		return "redirect:/member/list";
	}

	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("member", memberRepository.findAll());
		return "member/list";
	}
}
