package com.ttd.board;

import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

	@GetMapping("list")
	public String toList(Model model) {
		model.addAttribute("questions", Arrays.asList("1","2","3","4"));
		return "board/list";
	}
}
