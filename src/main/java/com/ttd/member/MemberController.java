package com.ttd.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberRepository memberRepository;

	@GetMapping("join")
	public ModelAndView join() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView create(Member member) {
		ModelAndView mv = new ModelAndView();
		System.out.println(member);
		memberRepository.save(member);
		mv.setViewName("redirect:/member/list");
		return mv;
	}

	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("member", memberRepository.findAll());
		mv.setViewName("member/list");
		return mv;
	}
}
