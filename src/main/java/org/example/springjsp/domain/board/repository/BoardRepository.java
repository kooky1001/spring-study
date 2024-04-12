package org.example.springjsp.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.example.springjsp.domain.board.Board;

public interface BoardRepository {
	Board save(Board board);

	List<Board> findAll();

	Optional<Board> findById(Long id);

	Board update(Long id, Board updateParam);

	Long delete(Board board);
}
