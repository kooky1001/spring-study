package com.ttd.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@GetMapping("/join")
	public ModelAndView join() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("join");
		return mv;
	}
	
	@PostMapping("/join")
	public ModelAndView create(User user) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		mv.setViewName("redirect:/");
		return mv;
	}
}
