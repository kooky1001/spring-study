package org.example.springjsp.domain.todolist.category;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "카테고리")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class Category {
	@Schema(description = "카테고리 id", example = "1")
	private Long id;
	@Schema(description = "카테고리 이름", example = "기타")
	private String name;
	@Schema(description = "카테고리 사용여부 - 현재 사용하지 않음", example = "true", deprecated = true)
	private boolean active;

	@Builder
	public Category(String name, boolean active) {
		this.name = name;
		this.active = active;
	}
}
