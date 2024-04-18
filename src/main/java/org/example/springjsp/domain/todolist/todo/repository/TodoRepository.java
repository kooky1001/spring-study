package org.example.springjsp.domain.todolist.todo.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.springjsp.domain.todolist.todo.Todo;

public interface TodoRepository {

	Todo save(Todo todo);

	List<Todo> findAll(LocalDate date);

	Long update(Long id, Todo updateParam);

	Long delete(Todo todo);

	Optional<Todo> findById(Long id);

	List<Todo> findAllByCategory(Long category);

	List<Todo> findAllByComplete();

	void deleteAllByCategoryId(Long category);
}
