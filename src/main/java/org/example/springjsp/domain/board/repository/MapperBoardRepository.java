package org.example.springjsp.domain.board.repository;

import java.util.List;
import java.util.Optional;

import org.example.springjsp.domain.board.Board;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Primary
@Repository
public class MapperBoardRepository implements BoardRepository {

	private final BoardMapper boardMapper;

	@Override
	public Board save(Board board) {
		board.setAuthor("testuser");
		boardMapper.save(board);
		return board;
	}

	@Override
	public List<Board> findAll() {
		List<Board> boardList = boardMapper.findAll();
		return boardList;
	}

	@Override
	public Optional<Board> findById(Long id) {
		return boardMapper.findById(id);
	}

	@Override
	public Board update(Long id, Board updateParam) {
		boardMapper.update(id, updateParam);
		return boardMapper.findById(id).orElse(null);
	}

	@Override
	public Long delete(Board board) {
		Long id = board.getId();
		boardMapper.delete(id);
		return id;
	}
}
