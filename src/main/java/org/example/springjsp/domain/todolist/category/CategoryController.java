package org.example.springjsp.domain.todolist.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
	@Parameter(name = "id", description = "카테고리 id", example = "1", in = ParameterIn.PATH)
	@DeleteMapping("{id}")
	public Category delete(@PathVariable Long id) {
		Category findCategory = categoryService.findOne(id);
		categoryService.delete(id);
		return findCategory;
	}

	@Operation(summary = "카테고리 저장", description = "신규 카테고리 추가")
	@Parameter(name = "name", description = "카테고리 이름", example = "기타")
	@ApiResponse(responseCode = "201")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public Category save(@RequestBody Category category) {
		if (category.getName() == null || category.getName().isBlank()) {
			throw new IllegalArgumentException("카테고리 이름은 필수입니다.");
		}
		return categoryService.save(category);
	}
}
