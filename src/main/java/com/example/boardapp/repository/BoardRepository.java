package com.example.boardapp.repository;

import com.example.boardapp.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 기본 CRUD 메서드는 JpaRepository에서 자동으로 제공됨
}