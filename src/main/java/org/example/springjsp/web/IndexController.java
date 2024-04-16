package org.example.springjsp.web;

import java.util.List;

import org.example.springjsp.domain.board.Board;
import org.example.springjsp.domain.board.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

	private final BoardService boardService;

	@GetMapping("/")
	public String index(Model model) {
		List<Board> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		return "index";
	}

	@GetMapping("/click/{name}")
	public String click(Model model, @PathVariable(value = "name", required = false) String name) {
		System.out.println("name = " + name);
		model.addAttribute("name", name);
		return "test/click";
	}

}
