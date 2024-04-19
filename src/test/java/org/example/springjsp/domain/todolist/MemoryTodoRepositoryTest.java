package org.example.springjsp.domain.todolist;

import static org.assertj.core.api.Assertions.*;

import org.example.springjsp.domain.todolist.todo.Todo;
import org.example.springjsp.domain.todolist.todo.repository.MemoryTodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryTodoRepositoryTest {

	MemoryTodoRepository memoryTodoRepository = new MemoryTodoRepository();

	@AfterEach
	void tearDown() {
		memoryTodoRepository.clear();
	}

	@Test
	void save() {
		String content = "테스트 할일";
		Todo todo = Todo.builder().content(content)
			// .date(LocalDate.now())
			.completed(false)
			.build();

		Todo savedTodo = memoryTodoRepository.save(todo);

		assertThat(savedTodo).isNotNull();
		assertThat(savedTodo.getContent()).isEqualTo(content);
	}

	@Test
	void list() {
		String content = "테스트 할일";
		Todo todo = Todo.builder().content(content)
			// .date(LocalDate.now())
			.completed(false)
			.build();

		Todo savedTodo = memoryTodoRepository.save(todo);

		// List<Todo> list = memoryTodoRepository.findAll(LocalDate.now());
		// System.out.println(list);
		// assertThat(list).contains(todo);
	}
}
