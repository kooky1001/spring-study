package org.example.springjsp.domain.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/todolist")
@Controller
public class TodoController {

	@GetMapping
	public String index() {
		return "todolist/list";
	}
}
