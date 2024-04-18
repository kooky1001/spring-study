package org.example.springjsp.domain.todolist;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.example.springjsp.domain.todolist.todo.Todo;
import org.example.springjsp.domain.todolist.todo.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoServiceTest {

	@Autowired
	private TodoService todoService;

	@Test
	void list() {

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
	void save() {
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
	void check() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		Todo updateParam = Todo.builder().content(todo.getContent())
			.completed(true)
			.build();

		todoService.update(savedTodo.getId(), updateParam);

		Todo findTodo = todoService.findById(savedTodo.getId());

		assertThat(true).isEqualTo(findTodo.getCompleted());
	}

	@Test
	void delete() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		todoService.delete(savedTodo);
		List<Todo> list = todoService.findAll(LocalDate.now());

		assertThat(list).doesNotContain(savedTodo);
	}

	@Test
	void update() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			.date(LocalDate.now())
			.build();
		Todo savedTodo = todoService.save(todo);

		String content = "테스트";
		Todo updateParam = Todo.builder().content(content)
			.completed(false)
			.build();
		todoService.update(savedTodo.getId(), updateParam);
		Todo findTodo = todoService.findById(savedTodo.getId());

		assertThat(findTodo.getContent()).isEqualTo(content);
	}

}
