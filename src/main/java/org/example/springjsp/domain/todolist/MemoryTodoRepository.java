package org.example.springjsp.domain.todolist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<Todo> findAll() {
		return new ArrayList<>(store.values());
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

}
