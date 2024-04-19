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
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	@Builder
	public Board(String title, String content, String author, LocalDateTime modifiedDate) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.modifiedDate = modifiedDate;
	}
}
