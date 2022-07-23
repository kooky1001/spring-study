package com.ttd.board;

import java.util.Arrays;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttd.user.User;
import com.ttd.web.MySessionUtils;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardRepository boardRepository;
	
	@GetMapping("list")
	public String toList(Model model) {
		model.addAttribute("list", boardRepository.findAll());
		return "board/list";
	}

	@GetMapping("form")
	public String toCreate() {
		return "board/form";
	}
	
	@PostMapping("form")
	public String create(String title, String content, HttpSession session) {
		if (!MySessionUtils.isLogin(session)) {
			return "redirect:/user/login";
		}
		User user = MySessionUtils.getLoginUser(session);
		boardRepository.save(new Board(user, title, content));
		return "redirect:/board/list";
	}
}
