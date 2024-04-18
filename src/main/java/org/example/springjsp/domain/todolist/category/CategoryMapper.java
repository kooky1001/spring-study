package org.example.springjsp.domain.todolist.category;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
	List<Category> findAll();

	// List<Category> findAllByActive();

	Long save(Category category);

	Long delete(Category category);

	Optional<Category> findById(Long id);

	// Long update(@Param("id") Long id, @Param("category") Category updateParam);
}
