package org.example.springjsp.domain.todolist;

import java.util.List;

public interface TodoRepository {

	Todo save(Todo todo);

	List<Todo> findAll();

	Todo update(Long id, Todo updateParam);

	Long delete(Todo todo);
}
