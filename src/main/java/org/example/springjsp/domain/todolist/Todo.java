package org.example.springjsp.domain.todolist;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	private Long id;
	private String content;
	private boolean completed;
	private LocalDate toDate;
	private Category category;
	//	private LocalDateTime createdDate;
	//	private LocalDateTime modifiedDate;

	@Builder
	public Todo(String content, boolean completed, LocalDate date, Category category) {
		this.content = content;
		this.completed = completed;
		this.toDate = date;
		this.category = category;
	}
}
