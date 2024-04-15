package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@ResponseBody
	@PostMapping
	public Todo save(@ModelAttribute Todo todo) {
		return todoService.save(todo);
	}

	@ResponseBody
	@GetMapping("list")
	public List<Todo> list(@RequestParam LocalDate date) {
		return todoService.findAll(date);
	}

	@ResponseBody
	@PutMapping("list/checked")
	public Todo check(@RequestParam Long id, @RequestParam Boolean completed) {
		return todoService.check(id, completed);
	}

	@ResponseBody
	@DeleteMapping
	public Long delete(@RequestParam Long id) {
		return todoService.delete(id);
	}

}
