package org.example.springjsp.domain.todolist.todo;

import static org.assertj.core.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class TodoServiceTest {

	@Autowired
	private TodoService todoService;

	@Test
	void save() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();

		//when
		Todo save = todoService.save(todo);
		Todo find = todoService.findById(save.getId());

		//then
		// assertThat(todo.getId()).isEqualTo(save.getId());
		// assertThat(todo.getContent()).isEqualTo(find.getContent());
		// assertThat(todo.getCategory()).isEqualTo(find.getCategory());
		assertThat(find).usingComparator(new Comparator<Todo>() {
			@Override
			public int compare(Todo o1, Todo o2) {
				int category = o1.getCategory().compareTo(o2.getCategory());
				int content = o1.getContent().compareTo(o2.getContent());
				int id = o1.getId().compareTo(o2.getId());
				return id + content + category;
			}
		}).isEqualTo(todo);
	}

	@Test
	void update() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();
		todoService.save(todo);

		Todo updateParam = Todo.builder()
			.completed(true)
			.build();
		Todo update = todoService.update(todo.getId(), updateParam);

		assertThat(update.getId()).isEqualTo(todo.getId());
		assertThat(update.getContent()).isEqualTo(todo.getContent());
		assertThat(update.getCategory()).isEqualTo(todo.getCategory());
		assertThat(update.getCompleted()).isEqualTo(updateParam.getCompleted());
	}

	@Test
	void updateAll() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();
		todoService.save(todo);

		Todo updateParam = Todo.builder().content("테스트")
			.completed(true)
			.build();
		// Todo update = todoService.updateAll(todo.getId(), updateParam);
		Todo update = todoService.update(todo.getId(), updateParam);

		assertThat(update.getId()).isEqualTo(todo.getId());
		assertThat(update.getContent()).isEqualTo(updateParam.getContent());
		assertThat(update.getCategory()).isEqualTo(todo.getCategory());
		assertThat(update.getCompleted()).isEqualTo(updateParam.getCompleted());
	}

	@Test
	void delete() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();
		todoService.save(todo);

		todoService.delete(todo);
		assertThatThrownBy(() -> todoService.findById(todo.getId())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(String.valueOf(todo.getId()));
	}

	@Test
	void findById() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();
		todoService.save(todo);

		Todo find = todoService.findById(todo.getId());
		assertThat(find.getId()).isEqualTo(todo.getId());
		assertThat(find.getContent()).isEqualTo(todo.getContent());
		assertThat(find.getCompleted()).isEqualTo(todo.getCompleted());
		assertThat(find.getCategory()).isEqualTo(todo.getCategory());
	}

	@Test
	void findAllByCategory() {
		Todo todo = Todo.builder()
			.content("test")
			.completed(false)
			// .date(LocalDate.now())
			.category(1L)
			.build();
		todoService.save(todo);

		List<Todo> todoList = todoService.findAllByCategory(1L);

		assertThat(todoList).filteredOn(t -> t.getId().equals(todo.getId()))
			.extracting(Todo::getId, Todo::getContent, Todo::getCompleted, Todo::getCategory)
			.contains(tuple(todo.getId(), todo.getContent(), todo.getCompleted(), todo.getCategory()));
	}

}
