package com.ttd.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("form")
	public String form() {
		return "user/form";
	}
	
	@PostMapping("form")
	public String create(User user) {
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/user/list";
	}

	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("user", userRepository.findAll());
		return "user/list";
	}
}
