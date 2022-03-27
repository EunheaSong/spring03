package com.board.spring03.repository;

import com.board.spring03.model.BoardWrite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardWrite, Long> {
    List<BoardWrite> findAllByOrderByModifiedAtDesc();
}
