package org.example.springjsp.domain.todolist.category;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "TodoList Category", description = "투두리스트의 카테고리 관련 Api")
@RequiredArgsConstructor
@RequestMapping("/todolist/category")
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@Operation(summary = "카테고리 조회", description = "카테고리 목록을 조회")
	@GetMapping
	public List<Category> findAll() {
		return categoryService.findAll();
	}

	@Operation(summary = "카테고리 삭제", description = "목록에 있는 카테고리 삭제 및 관련된 할 일 삭제")
	@Parameter(name = "id", description = "카테고리 id", example = "1")
	@DeleteMapping
	public Category delete(@RequestParam Long id) {
		Category category = categoryService.findOne(id);
		categoryService.delete(id);
		return category;
	}

	@Operation(summary = "카테고리 저장", description = "신규 카테고리 추가")
	@Parameter(name = "description", description = "카테고리 이름", example = "기타")
	@PostMapping
	public Category save(@RequestParam String description) {
		Category category = Category.builder().description(description).build();
		return categoryService.save(category);
	}
}
