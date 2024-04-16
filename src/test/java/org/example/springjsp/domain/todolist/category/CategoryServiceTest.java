package org.example.springjsp.domain.todolist.category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	void findAll() {
		Category category = Category.builder()
			.description("테스트")
			.active(true)
			.build();

		categoryService.save(category);

		List<Category> categories = categoryService.findAll();
		System.out.println("categories = " + categories);
		// assertThat(categories).contains(category);
	}

	@Test
	void findAllByActive() {
		Category category = Category.builder()
			.description("테스트")
			.active(false)
			.build();

		categoryService.save(category);

		List<Category> categories = categoryService.findAllByActive();
		System.out.println("categories = " + categories);
	}

	@Test
	void findOne() {
		Category category = Category.builder()
			.description("테스트")
			.active(true)
			.build();

		Category saved = categoryService.save(category);
		Category find = categoryService.findOne(saved.getId());

		assertThat(saved.getId()).isEqualTo(find.getId());
	}

	@Test
	void save() {
		Category category = Category.builder()
			.description("테스트")
			.active(true)
			.build();

		Category saved = categoryService.save(category);

		assertThat(saved.getId()).isNotNull();
	}

	@Test
	void delete() {
		Category category = Category.builder()
			.description("테스트")
			.active(true)
			.build();

		Category saved = categoryService.save(category);
		categoryService.delete(saved.getId());

		assertThat(categoryService.findOne(saved.getId())).isNull();
	}

	@Test
	void update() {
		Category category = Category.builder()
			.description("테스트")
			.active(true)
			.build();
		Category saved = categoryService.save(category);

		categoryService.update(saved.getId(), Category.builder().active(false).build());

		assertThat(categoryService.findOne(saved.getId()).isActive()).isEqualTo(false);
	}

}
