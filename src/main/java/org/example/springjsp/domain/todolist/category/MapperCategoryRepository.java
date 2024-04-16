package org.example.springjsp.domain.todolist.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MapperCategoryRepository implements CategoryRepository {

	private final CategoryMapper categoryMapper;

	@Override
	public List<Category> findAll() {
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findAllByActive() {
		return categoryMapper.findAllByActive();
	}

	@Override
	public Category save(Category category) {
		categoryMapper.save(category);
		return category;
	}

	@Override
	public Long delete(Category category) {
		Long id = category.getId();
		categoryMapper.delete(category);
		return id;
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryMapper.findById(id);
	}

	@Override
	public Category update(Long id, Category updateParam) {
		categoryMapper.update(id, updateParam);
		return findById(id).orElse(null);
	}
}
