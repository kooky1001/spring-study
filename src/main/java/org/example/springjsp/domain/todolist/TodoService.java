package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;

	@PostConstruct
	public void init() {
		Todo todo1 = Todo.builder()
			.content("해야할일 1")
			.completed(false)
			.date(LocalDate.of(2024, 4, 14)).build();
		Todo todo2 = Todo.builder()
			.content("해야할일 2")
			.completed(false)
			.date(LocalDate.of(2024, 4, 14)).build();

		todoRepository.save(todo1);
		todoRepository.save(todo2);
	}

	public Todo save(Todo todo) {
		return todoRepository.save(todo);
	}

	public List<Todo> findAll() {
		return todoRepository.findAll();
	}
}
