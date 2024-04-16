package org.example.springjsp.domain.todolist;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Category {
	work("업무"),
	personal("개인"),
	other("기타");

	private final String description;
}
