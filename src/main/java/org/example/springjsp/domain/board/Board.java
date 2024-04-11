package org.example.springjsp.domain.board;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Board {
	private Long id;
	private String title;
	private String content;
	private String author;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	@Builder
	public Board(String title, String content, String author, LocalDateTime updatedAt) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.updatedAt = updatedAt;
	}
}
