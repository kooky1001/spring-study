package org.example.springjsp.domain.board;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardMapper {
	Long save(Board board);

	List<Board> findAll();

	Optional<Board> findById(Long id);

	Long update(@Param("id") Long id, @Param("board") Board updateParam);

	Long delete(Long id);
}
