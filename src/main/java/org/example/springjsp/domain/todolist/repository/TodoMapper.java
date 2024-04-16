package org.example.springjsp.domain.todolist.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.springjsp.domain.todolist.Category;
import org.example.springjsp.domain.todolist.Todo;

@Mapper
public interface TodoMapper {
	Long save(Todo todo);

	List<Todo> findAll(LocalDate toDate);

	Optional<Todo> findById(Long id);

	Long update(@Param("id") Long id, @Param("todo") Todo updateParam);

	Long delete(Long id);

	List<Todo> findAllByCategory(Category category);

	List<Todo> findAllByComplete();
}
