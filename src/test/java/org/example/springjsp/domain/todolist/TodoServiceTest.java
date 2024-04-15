package org.example.springjsp.domain.todolist;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoServiceTest {

	@Autowired
	private TodoService todoService;

	@Test
	void 목록조회() {

		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		todoService.save(todo);

		List<Todo> list = todoService.findAll(LocalDate.now());
		assertThat(list).contains(todo);

	}

	@Test
	void 저장() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		Todo findTodo = todoService.findById(savedTodo.getId());

		assertThat(todo).isEqualTo(findTodo);
	}

	@Test
	void 체크() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		todoService.check(savedTodo.getId(), true);

		Todo findTodo = todoService.findById(savedTodo.getId());

		assertThat(true).isEqualTo(findTodo.isCompleted());
	}

}
