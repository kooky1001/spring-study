package com.ttd.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttd.domain.model.Board;
import com.ttd.domain.model.Reply;
import com.ttd.domain.model.User;
import com.ttd.domain.model.Validation;
import com.ttd.domain.repository.BoardRepository;
import com.ttd.domain.repository.ReplyRepository;

@Controller
@RequestMapping("/board/{id}/reply")
public class ReplyController {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	
	//@PostMapping("create")
	public String create(@PathVariable long boardId, String content, HttpSession session, Model model) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		User user = CustomUtils.getLoginUser(session);
		Board board = boardRepository.findById(boardId).orElse(null);
		replyRepository.save(new Reply(user, board, content));
		return String.format("redirect:/board/%d/detail", boardId);
	}
	
	@ResponseBody
	@PostMapping("create")
	public Reply createApi(@PathVariable long id, String content, HttpSession session) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			return null;
		}
		User user = CustomUtils.getLoginUser(session);
		Board board = boardRepository.findById(id).orElse(null);
		return replyRepository.save(new Reply(user, board, content));
	}
	
	//@GetMapping("{replyId}/delete")
	public String delete(@PathVariable long boardId, @PathVariable long replyId, HttpSession session, Model model) {
		Reply reply = replyRepository.findById(replyId).orElse(null);
		Validation validation = validate(session, reply);
		if (!validation.isValid()) {
			model.addAttribute(CustomUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		replyRepository.delete(reply);
		return String.format("redirect:/board/%d/detail", boardId);
	}
	
	@ResponseBody
	@DeleteMapping("{replyId}/delete")
	public Validation deleteApi(@PathVariable long id, @PathVariable long replyId, HttpSession session) {
		Reply reply = replyRepository.findById(replyId).orElse(null);
		Validation validation = validate(session, reply);
		if (!validation.isValid()) {
			return validation;
		}
		replyRepository.delete(reply);
		return validation;
	}
	
	private Validation validate(HttpSession session) {
		return validate(session, null);
	}
	private Validation validate(HttpSession session, Reply reply) {
		if (!CustomUtils.isLogin(session)) {
			return Validation.fail("로그인이 필요합니다.");
		}
		User loginUser = CustomUtils.getLoginUser(session);
		if (reply != null && !reply.isSameUser(loginUser)) {
			return Validation.fail("권한이 없습니다.");
		}
		return Validation.ok();
	}
}
