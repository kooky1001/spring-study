package org.example.springjsp.domain.todolist;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	private Long id;
	private String content;
	private boolean completed;
	private LocalDate date;
	//	private LocalDateTime createdDate;
	//	private LocalDateTime modifiedDate;

	@Builder
	public Todo(String content, boolean completed, LocalDate date) {
		this.content = content;
		this.completed = completed;
		this.date = date;
	}
}
