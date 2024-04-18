package org.example.springjsp.domain.todolist.category;

import java.util.List;

import org.example.springjsp.domain.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final TodoRepository todoRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public List<Category> findAllByActive() {
		return categoryRepository.findAllByActive();
	}

	public Category findOne(Long id) {
		return categoryRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. id: " + id));
	}

	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	public Long delete(Long id) {
		Category category = findOne(id);
		todoRepository.deleteAllByCategoryId(category.getId());
		return categoryRepository.delete(category);
	}

	public Category update(Long id, Category updateParam) {
		Category category = Category.builder()
			.active(updateParam.isActive())
			.build();
		return categoryRepository.update(id, updateParam);
	}
}
