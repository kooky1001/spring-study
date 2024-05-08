package org.example.springjsp.domain.todolist.todo;

import java.util.List;

import org.example.springjsp.domain.todolist.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TodoService {

	private final TodoRepository todoRepository;

	public Todo save(Todo todo) {
		if (todo.getCompleted() == null) {
			todo = Todo.builder()
				.content(todo.getContent())
				.completed(false)
				.category(todo.getCategory())
				.build();
		}
		return todoRepository.save(todo);
	}

	// public List<Todo> findAll(LocalDate toDate) {
	// 	return todoRepository.findAll(toDate);
	// }

	public Todo findById(Long id) {
		return todoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 할 일이 없습니다. id: " + id));
	}

	public Todo update(Long id, Todo updateParam) {
		Todo findTodo = findById(id);
		String content =
			((updateParam.getContent() == null) || updateParam.getContent().isBlank()) ? findTodo.getContent() :
				updateParam.getContent();
		boolean completed = updateParam.getCompleted() == null ? findTodo.getCompleted() : updateParam.getCompleted();
		findTodo.update(content, completed);
		todoRepository.update(id, findTodo);
		return findTodo;
	}

	// public Todo updateAll(Long id, Todo updateParam) {
	// 	Todo findTodo = findById(id);
	// 	boolean completed = updateParam.getCompleted() == null ? findTodo.getCompleted() : updateParam.getCompleted();
	// 	findTodo.update(updateParam.getContent(), completed);
	// 	todoRepository.update(id, findTodo);
	// 	return findTodo;
	// }

	public Long delete(Todo todo) {
		return todoRepository.delete(todo);
	}

	public List<Todo> findAllByCategory(Long category) {
		return todoRepository.findAllByCategory(category);
	}

}
