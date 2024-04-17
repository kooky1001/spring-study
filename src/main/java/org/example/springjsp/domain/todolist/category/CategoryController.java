package org.example.springjsp.domain.todolist.category;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/todolist/category")
@RestController
public class CategoryController {

	private final CategoryService categoryService;

	@GetMapping
	public List<Category> findAll() {
		return categoryService.findAll();
	}
}
