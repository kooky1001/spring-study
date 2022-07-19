package com.ttd.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private List<User> list = new ArrayList<User>();

	@GetMapping("join")
	public ModelAndView join() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/user/join");
		return mv;
	}
	
	@PostMapping("join")
	public ModelAndView create(User user) {
		ModelAndView mv = new ModelAndView();
		System.out.println(user);
		list.add(user);
		mv.setViewName("redirect:/user/list");
		return mv;
	}

	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", list);
		mv.setViewName("/user/list");
		return mv;
	}
}
