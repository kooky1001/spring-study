package com.ttd.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttd.domain.model.User;
import com.ttd.domain.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("form")
	public String toCreate() {
		return "user/form";
	}
	
	@PostMapping("form")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/user/list";
	}

	@GetMapping("list")
	public String toList(Model model) {
		model.addAttribute("user", userRepository.findAll());
		return "user/list";
	}
	
	@GetMapping("{id}/update")
	public String toUpdate(@PathVariable long id, Model model, HttpSession session) {
		if (!CustomUtils.isLogin(session)) {
			return "redirect:/user/login";
		}
		User loginUser = CustomUtils.getLoginUser(session);
		if (!loginUser.matchId(id)) {
			throw new IllegalStateException("You approached it through an illegal way.");
		}
		
		User user = userRepository.findById(id).orElse(null);
		model.addAttribute("user", user);
		return "user/update";
	}
	
	@PostMapping("{id}/update")
	public String update(@PathVariable long id, User updateUser, HttpSession session) {
		if (!CustomUtils.isLogin(session)) {
			return "redirect:/user/login";
		}
		User loginUser = CustomUtils.getLoginUser(session);
		if (!loginUser.matchId(id)) {
			throw new IllegalStateException("You approached it through an illegal way.");
		}
		
		User user = userRepository.findById(id).orElse(null);
		if (user != null) {
			user.update(updateUser);
			userRepository.save(user);
		}
		return "redirect:/user/list";
	}
	
	@GetMapping("login")
	public String toLogin() {
		return "user/login";
	}
	
	@PostMapping("login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			return "redirect:/user/login";
		} else if (!user.matchPassword(password)) {
			return "redirect:/user/login";
		}
		session.setAttribute(CustomUtils.USER_KEY, user);
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.removeAttribute(CustomUtils.USER_KEY);
		return "redirect:/";
	}
}
