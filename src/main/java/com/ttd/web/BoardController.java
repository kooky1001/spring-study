package com.ttd.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ttd.domain.Board;
import com.ttd.domain.BoardRepository;
import com.ttd.domain.User;
import com.ttd.domain.Validation;

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
	public String toCreate(HttpSession session, Model model) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		return "board/form";
	}
	
	@PostMapping("form")
	public String create(String title, String content, HttpSession session, Model model) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		User user = CustomUtils.getLoginUser(session);
		boardRepository.save(new Board(user, title, content));
		return "redirect:/board/list";
	}

	@GetMapping("{id}/detail")
	public String toDetail(@PathVariable long id, Model model) {
		model.addAttribute("board", boardRepository.findById(id).orElse(null));
		return "board/detail";
	}
	
	@GetMapping("{id}/update")
	public String toUpdate(@PathVariable long id, Model model, HttpSession session) {
		Board board =  boardRepository.findById(id).orElse(null);
		Validation validation = validate(session, board);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		model.addAttribute("board", board);
		return "board/update";
	}
	
	@PostMapping("{id}/update")
	public String update(@PathVariable long id, String title, String content, HttpSession session, Model model) {
		Board board = boardRepository.findById(id).orElse(null);
		Validation validation = validate(session, board);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		board.update(title, content);
		boardRepository.save(board);
		return String.format("redirect:/board/%d/detail", id);
	}

	@GetMapping("{id}/delete")
	public String delete(@PathVariable long id, HttpSession session, Model model) {
		Board board = boardRepository.findById(id).orElse(null);
		Validation validation = validate(session, board);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		boardRepository.delete(board);
		return String.format("redirect:/board/list");
	}
	
	private Validation validate(HttpSession session) {
		return validate(session, null);
	}
	
	private Validation validate(HttpSession session, Board board) {
		if(!CustomUtils.isLogin(session)) {
			return Validation.fail("로그인이 필요합니다.");
		}
		User loginUser = CustomUtils.getLoginUser(session);
		if (board != null && !board.isSameUser(loginUser)) {
			return Validation.fail("권한이 없습니다.");
		}
		return Validation.ok();
	}
}
