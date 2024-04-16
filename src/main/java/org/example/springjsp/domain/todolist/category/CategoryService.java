package org.example.springjsp.domain.todolist.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public List<Category> findAllByActive() {
		return categoryRepository.findAllByActive();
	}

	public Category findOne(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Long delete(Long id) {
		Category category = findOne(id);
		return categoryRepository.delete(category);
	}

	public Category update(Long id, Category updateParam) {
		Category category = Category.builder()
			.active(updateParam.isActive())
			.build();
		return categoryRepository.update(id, updateParam);
	}
}
