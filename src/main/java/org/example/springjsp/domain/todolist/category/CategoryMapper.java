package org.example.springjsp.domain.todolist.category;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {
	List<Category> findAll();

	List<Category> findAllByActive();

	Long save(Category category);

	Long delete(Category category);

	Optional<Category> findById(Long id);
	// Map<String, String> findById(Long id);

	Long update(@Param("id") Long id, @Param("category") Category updateParam);
}
