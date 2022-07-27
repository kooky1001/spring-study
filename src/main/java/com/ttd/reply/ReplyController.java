package com.ttd.reply;

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

import com.ttd.board.Board;
import com.ttd.board.BoardRepository;
import com.ttd.user.User;
import com.ttd.web.MySessionUtils;
import com.ttd.web.Validation;

@Controller
@RequestMapping("/board/{boardId}/reply")
public class ReplyController {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private ReplyRepository replyRepository;
	
	//@PostMapping("create")
	public String create(@PathVariable long boardId, String content, HttpSession session, Model model) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			model.addAttribute(MySessionUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		User user = MySessionUtils.getLoginUser(session);
		Board board = boardRepository.findById(boardId).orElse(null);
		replyRepository.save(new Reply(user, board, content));
		return String.format("redirect:/board/%d/detail", boardId);
	}
	
	@PostMapping("create")
	public Reply createApi(@PathVariable long boardId, String content, HttpSession session) {
		Validation validation = validate(session);
		if (!validation.isValid()) {
			return null;
		}
		User user = MySessionUtils.getLoginUser(session);
		Board board = boardRepository.findById(boardId).orElse(null);
		return replyRepository.save(new Reply(user, board, content));
	}
	
	//@GetMapping("{replyId}/delete")
	public String delete(@PathVariable long boardId, @PathVariable long replyId, HttpSession session, Model model) {
		Reply reply = replyRepository.findById(replyId).orElse(null);
		Validation validation = validate(session, reply);
		if (!validation.isValid()) {
			model.addAttribute(MySessionUtils.MESSAGE, validation.getMessage());
			return "user/login";
		}
		replyRepository.delete(reply);
		return String.format("redirect:/board/%d/detail", boardId);
	}
	
	@ResponseBody
	@DeleteMapping("{replyId}/delete")
	public Validation deleteApi(@PathVariable long boardId, @PathVariable long replyId, HttpSession session) {
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
		if (!MySessionUtils.isLogin(session)) {
			return Validation.fail("로그인이 필요합니다.");
		}
		User loginUser = MySessionUtils.getLoginUser(session);
		if (reply != null && !reply.isSameUser(loginUser)) {
			return Validation.fail("권한이 없습니다.");
		}
		return Validation.ok();
	}
}
