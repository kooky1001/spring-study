package org.example.springjsp.domain.todolist.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class Category {
	private Long id;
	private String description;
	private boolean active;

	@Builder
	public Category(String description, boolean active) {
		this.description = description;
		this.active = active;
	}
}
