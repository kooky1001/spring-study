package org.example.springjsp.domain.todolist;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
	private Long id;
	private String content;
	private boolean completed;
	private LocalDate date;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
}
