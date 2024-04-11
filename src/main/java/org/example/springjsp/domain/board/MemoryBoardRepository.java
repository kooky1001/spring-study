package org.example.springjsp.domain.board;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository{

    private static final Map<Long, Board> store = new HashMap<>();
    private static Long sequence = 0L;

//    @PostConstruct
//    public void init() {
//        this.save(Board.builder().content("test1").build());
//        this.save(Board.builder().content("test2").build());
//    }

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
