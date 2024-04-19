package org.example.springjsp.domain.todolist.todo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.springjsp.domain.todolist.todo.Todo;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryTodoRepository implements TodoRepository {

	private static final Map<Long, Todo> store = new HashMap<>();
	private static Long sequence = 0L;

	@Override
	public Todo save(Todo todo) {
		// todo.setId(++sequence);
		store.put(todo.getId(), todo);
		return todo;
	}

	// @Override
	// public List<Todo> findAll(LocalDate toDate) {
	// 	// return store.values().stream().filter(todo -> todo.getToDate().isEqual(toDate)).collect(Collectors.toList());
	// 	return null;
	// }

	@Override
	public Long update(Long id, Todo updateParam) {
		Todo todo = store.get(id);
		// todo.setCompleted(updateParam.isCompleted());
		// todo.setContent(updateParam.getContent());
		return id;
	}

	@Override
	public Long delete(Todo todo) {
		store.remove(todo.getId());
		return todo.getId();
	}

	@Override
	public Optional<Todo> findById(Long id) {
		return Optional.of(store.get(id));
	}

	public void clear() {
		store.clear();
	}

	@Override
	public List<Todo> findAllByCategory(Long category) {
		return List.of();
	}

	@Override
	public void deleteAllByCategoryId(Long id) {
	}
}
