package org.example.springjsp.domain.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Board save(Board board) {
        return boardRepository.save(board);
    }

    public Board update(Long id, Board updateParam) {
        return boardRepository.update(id, updateParam);
    }

    public Long delete(Long id) {
        boardRepository.findById(id).map(boardRepository::delete)
                        .orElseThrow(IllegalArgumentException::new);
//        boardRepository.delete(id);
        return id;
    }
}
