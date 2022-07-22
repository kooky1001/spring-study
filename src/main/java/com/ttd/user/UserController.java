package com.ttd.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("{id}/update")
	public String updateForm(Model model, @PathVariable long id) {
		Optional<User> user = userRepository.findById(id);
		model.addAttribute("user", user.get());
		return "user/update";
	}
	
	@PostMapping("{id}/update")
	public String update(User updateUser, @PathVariable long id) {
		Optional<User> searchUser = userRepository.findById(id);
		User user = searchUser.get();
		user.update(updateUser);
		userRepository.save(user);
		return "redirect:/user/list";
	}
}
