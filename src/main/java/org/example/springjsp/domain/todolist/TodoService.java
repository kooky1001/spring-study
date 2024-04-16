package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.example.springjsp.domain.todolist.category.Category;
import org.example.springjsp.domain.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;

	// @PostConstruct
	// public void init() {
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

	public List<Todo> findAllByCategory(Category category) {
		return todoRepository.findAllByCategory(category);
	}

	public List<Todo> findAllByComplete() {
		return todoRepository.findAllByComplete();
	}
}
