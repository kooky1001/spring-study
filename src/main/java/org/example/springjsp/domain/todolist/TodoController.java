package org.example.springjsp.domain.todolist;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/todolist")
@Controller
public class TodoController {

	private final TodoService todoService;

	@GetMapping
	public String index() {
		return "todolist/list";
	}

	@PostMapping
	public Todo save(@ModelAttribute Todo todo) {

		return todoService.save(todo);
	}

	@ResponseBody
	@GetMapping("list")
	public List<Todo> list() {
		return todoService.findAll();
	}
}
