package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.util.List;

public interface TodoRepository {

	Todo save(Todo todo);

	List<Todo> findAll(LocalDate date);

	Todo update(Long id, Todo updateParam);

	Long delete(Todo todo);
}
