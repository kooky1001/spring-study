package com.ttd.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttd.domain.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
