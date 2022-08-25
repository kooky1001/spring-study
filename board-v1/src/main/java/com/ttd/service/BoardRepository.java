package com.ttd.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttd.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
