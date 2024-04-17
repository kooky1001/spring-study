package org.example.springjsp.domain.todolist.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.springjsp.domain.todolist.Todo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Primary
@RequiredArgsConstructor
@Repository
public class MapperTodoRepository implements TodoRepository {

	private final TodoMapper todoMapper;

	@Override
	public Todo save(Todo todo) {
		todoMapper.save(todo);
		return todo;
	}

	@Override
	public List<Todo> findAll(LocalDate date) {
		List<Todo> list = todoMapper.findAll(date);
		return list;
	}

	@Override
	public Todo update(Long id, Todo updateParam) {
		todoMapper.update(id, updateParam);
		return todoMapper.findById(id).orElse(null);
	}

	@Override
	public Long delete(Todo todo) {
		Long id = todo.getId();
		todoMapper.delete(id);
		return id;
	}

	@Override
	public Optional<Todo> findById(Long id) {
		return todoMapper.findById(id);
	}

	@Override
	public List<Todo> findAllByCategory(Long category) {
		return todoMapper.findAllByCategory(category);
	}

	@Override
	public List<Todo> findAllByComplete() {
		return todoMapper.findAllByComplete();
	}

	@Override
	public void deleteAllByCategoryId(Long category) {
		todoMapper.deleteAllByCategoryId(category);
	}
}
