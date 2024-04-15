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
		System.out.println("list = " + list);
		// assertThat(list).contains(todo);

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

		assertThat(todo.getContent()).isEqualTo(findTodo.getContent());
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

	@Test
	void 삭제() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		todoService.delete(savedTodo.getId());
		List<Todo> list = todoService.findAll(LocalDate.now());

		assertThat(list).doesNotContain(savedTodo);
	}

	@Test
	void 업데이트() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		String content = "테스트";
		todoService.update(savedTodo.getId(), content);
		Todo findTodo = todoService.findById(savedTodo.getId());

		assertThat(findTodo.getContent()).isEqualTo(content);
	}

}
