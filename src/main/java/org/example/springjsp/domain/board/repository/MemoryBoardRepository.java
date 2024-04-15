package org.example.springjsp.domain.board.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.example.springjsp.domain.board.Board;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryBoardRepository implements BoardRepository {

	private static final Map<Long, Board> store = new HashMap<>();
	private static Long sequence = 0L;

	@Override
	public Board save(Board board) {
		board.setId(++sequence);
		store.put(board.getId(), board);
		return board;
	}

	@Override
	public List<Board> findAll() {
		return new ArrayList<>(store.values());
	}

	@Override
	public Optional<Board> findById(Long id) {
		return Optional.of(store.get(id));
	}

	@Override
	public Board update(Long id, Board updateParam) {
		Board board = store.get(id);
		board.setContent(updateParam.getContent());
		return board;
	}

	public void clear() {
		store.clear();
	}

	@Override
	public Long delete(Board board) {
		Long id = board.getId();
		store.remove(id);
		return id;
	}
}
