package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryTodoRepository implements TodoRepository {

	private static final Map<Long, Todo> store = new HashMap<>();
	private static Long sequence = 0L;

	@Override
	public Todo save(Todo todo) {
		todo.setId(++sequence);
		store.put(todo.getId(), todo);
		return todo;
	}

	@Override
	public List<Todo> findAll(LocalDate toDate) {
		return store.values().stream().filter(todo -> todo.getToDate().isEqual(toDate)).collect(Collectors.toList());
	}

	@Override
	public Todo update(Long id, Todo updateParam) {
		Todo todo = store.get(id);
		todo.setCompleted(updateParam.isCompleted());
		todo.setContent(updateParam.getContent());
		return todo;
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

}
