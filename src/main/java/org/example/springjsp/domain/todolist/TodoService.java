package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;

	// @PostConstruct
	// public void init() {
	// 	Todo todo1 = Todo.builder()
	// 		.content("해야할일 1")
	// 		.completed(false)
	// 		.date(LocalDate.of(2024, 4, 15)).build();
	// 	Todo todo2 = Todo.builder()
	// 		.content("해야할일 2")
	// 		.completed(false)
	// 		.date(LocalDate.of(2024, 4, 14)).build();
	// 	Todo todo3 = Todo.builder()
	// 		.content("해야할일 3")
	// 		.completed(true)
	// 		.date(LocalDate.of(2024, 4, 15)).build();
	//
	// 	todoRepository.save(todo1);
	// 	todoRepository.save(todo2);
	// 	todoRepository.save(todo3);
	// }

	public Todo save(Todo todo) {
		return todoRepository.save(todo);
	}

	public List<Todo> findAll(LocalDate toDate) {
		return todoRepository.findAll(toDate);
	}

	public Todo findById(Long id) {
		return todoRepository.findById(id).orElse(null);
	}

	public Todo update(Long id, String content) {
		Todo todo = findById(id);
		Todo updateParam = Todo.builder()
			.content(content)
			.completed(todo.isCompleted())
			.build();
		return todoRepository.update(id, updateParam);
	}

	public Todo check(Long id, boolean completed) {
		Todo todo = findById(id);
		Todo updateParam = Todo.builder()
			.content(todo.getContent())
			.completed(completed)
			.build();
		return todoRepository.update(id, updateParam);
	}

	public Long delete(Long id) {
		Todo todo = findById(id);
		return todoRepository.delete(todo);
	}
}
