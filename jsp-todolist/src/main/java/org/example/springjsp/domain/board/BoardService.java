package org.example.springjsp.domain.board;

import java.util.List;

import org.example.springjsp.domain.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public List<Board> findAll() {
		return boardRepository.findAll();
	}

	public Board findOne(Long id) {
		return boardRepository.findById(id).orElse(null);
	}

	@Transactional
	public Board save(Board board) {
		return boardRepository.save(board);
	}

	@Transactional
	public Board update(Long id, Board updateParam) {
		return boardRepository.update(id, updateParam);
	}

	@Transactional
	public Long delete(Long id) {
		boardRepository.findById(id).map(boardRepository::delete).orElseThrow(IllegalArgumentException::new);
		//        boardRepository.delete(id);
		return id;
	}
}
