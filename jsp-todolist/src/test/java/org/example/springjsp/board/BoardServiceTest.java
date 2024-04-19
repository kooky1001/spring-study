package org.example.springjsp.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.example.springjsp.domain.board.Board;
import org.example.springjsp.domain.board.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class BoardServiceTest {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	void findAll() {
		String content = "test save";
		Board board = Board.builder().content(content).build();
		// Board board = new Board(content);

		Board savedBoard = boardRepository.save(board);

		List<Board> boardList = boardRepository.findAll();

		assertThat(boardList).contains(savedBoard);
	}

	@Test
	void findOne() {
		String content = "test save";
		Board board = Board.builder().content(content).build();
		// Board board = new Board(content);

		Board savedBoard = boardRepository.save(board);
		Board findBoard = boardRepository.findById(savedBoard.getId()).orElse(null);

		assertThat(content).isEqualTo(findBoard.getContent());

	}

	@Test
	void save() {
		String content = "test save";
		Board board = Board.builder().content(content).build();
		//        Board board = new Board(content);

		Board savedBoard = boardRepository.save(board);

		assertThat(content).isEqualTo(savedBoard.getContent());
	}

	@Test
	void update() {

		String content = "test save";
		Board board = Board.builder().content(content).build();
		//        Board board = new Board(content);
		Board savedBoard = boardRepository.save(board);

		String updateContent = "update content";
		Board updateParam = Board.builder().content(updateContent).build();
		//        Board updateParam = new Board(updateContent);
		Board updateBoard = boardRepository.update(savedBoard.getId(), updateParam);

		assertThat(updateContent).isEqualTo(updateBoard.getContent());

	}

	@Test
	void delete() {
		String content = "test save";
		Board board = Board.builder().content(content).build();
		//        Board board = new Board(content);
		Board savedBoard = boardRepository.save(board);

		boardRepository.delete(savedBoard);
		List<Board> boardList = boardRepository.findAll();

		assertThat(boardList).doesNotContain(savedBoard);
	}
}
