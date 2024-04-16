package org.example.springjsp.domain.todolist.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
	List<Category> findAll();

	List<Category> findAllByActive();

	Category save(Category category);

	Long delete(Category category);

	Optional<Category> findById(Long id);

	Category update(Long id, Category updateParam);
}
