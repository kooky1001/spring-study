package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todolist")
@RestController
public class TodoController {

	private final TodoService todoService;

	@PostMapping
	public Todo save(@ModelAttribute Todo todo) {
		return todoService.save(todo);
	}

	// 날짜별 조회
	// @ResponseBody
	// @GetMapping("list")
	public List<Todo> listByDate(@RequestParam LocalDate toDate) {
		return todoService.findAll(toDate);
	}

	@GetMapping("list")
	public List<Todo> listByCategory(@RequestParam Long category) {
		List<Todo> todoList = todoService.findAllByCategory(category);
		return todoList;
	}

	@GetMapping("complete")
	public List<Todo> listBycomplete() {
		List<Todo> todoList = todoService.findAllByComplete();
		return todoList;
	}

	@PutMapping("checked")
	public Todo check(@RequestParam Long id, @RequestParam Boolean completed) {
		return todoService.check(id, completed);
	}

	@DeleteMapping
	// public Long delete(@RequestParam Long id) {
	public Todo delete(@RequestParam Long id) {
		Todo todo = todoService.findById(id);
		todoService.delete(id);
		return todo;
	}

	@PutMapping
	public Todo update(@RequestParam Long id, @RequestParam String content) {
		return todoService.update(id, content);
	}

}
