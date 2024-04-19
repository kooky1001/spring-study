package org.example.springjsp.domain.todolist.todo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "할 일 내용")
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	@Schema(description = "할 일 id", example = "1")
	private Long id;
	@Schema(description = "할 일 내용", example = "오늘 할 일 내용입니다.")
	private String content;
	@Schema(description = "완료 여부", example = "false")
	private Boolean completed;
	// @Schema(description = "할 일의 날짜 - 현재 사용하지 않음", example = "null", deprecated = true)
	// private LocalDate toDate;
	@Schema(description = "카테고리 id", example = "1")
	private Long category;
	//	private LocalDateTime createdDate;
	//	private LocalDateTime modifiedDate;

	@Builder
	public Todo(String content, boolean completed, Long category) {
		this.content = content;
		this.completed = completed;
		this.category = category;
	}

	public void update(String content, boolean completed) {
		this.content = content;
		this.completed = completed;
	}
}
