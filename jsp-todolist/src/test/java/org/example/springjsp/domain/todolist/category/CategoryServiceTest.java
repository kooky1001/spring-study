package org.example.springjsp.domain.todolist.category;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	void findAll() {
		Category category = Category.builder()
			.name("테스트")
			.build();
		Category save = categoryService.save(category);

		//when
		List<Category> categoryList = categoryService.findAll();

		//then
		assertThat(categoryList).hasSizeGreaterThanOrEqualTo(1);
		// assertThat(categoryList).filteredOn(c -> c.getId().equals(save.getId()))
		// 	.extracting(Category::getId, Category::getName)
		// 	.contains(tuple(save.getId(), save.getName()));
		assertThat(categoryList)
			.extracting(Category::getId, Category::getName)
			// .containsExactly(tuple(save.getId(), save.getName()));
			.contains(tuple(save.getId(), save.getName()));
		// assertThat(categories).contains(category);
	}

	@Test
	void findOne() {
		Category category = Category.builder()
			.name("테스트")
			.build();

		Category save = categoryService.save(category);
		Category find = categoryService.findOne(save.getId());

		assertThat(save.getId()).isEqualTo(find.getId());
		assertThat(save.getName()).isEqualTo(find.getName());
	}

	@Test
	void save() {
		Category category = Category.builder()
			.name("테스트")
			.build();

		Category save = categoryService.save(category);

		assertThat(save.getId()).isNotNull();
	}

	@Test
	void delete() {
		Category category = Category.builder()
			.name("테스트")
			.build();

		Category save = categoryService.save(category);
		categoryService.delete(save.getId());

		assertThatThrownBy(() -> categoryService.findOne(save.getId())).isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining(String.valueOf(save.getId()));
	}

}
