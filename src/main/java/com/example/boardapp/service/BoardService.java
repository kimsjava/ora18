package com.example.boardapp.service;

import com.example.boardapp.dto.BoardDTO;
import com.example.boardapp.entity.Board;
import com.example.boardapp.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;
    
    // 모든 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // ID로 게시글 조회
    @Transactional(readOnly = true)
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + id + "인 게시글을 찾을 수 없습니다"));
        return convertToDTO(board);
    }
    
    // 게시글 저장
    @Transactional
    public BoardDTO saveBoard(BoardDTO boardDTO) {
        Board board = convertToEntity(boardDTO);
        board.prePersist();
        Board savedBoard = boardRepository.save(board);
        return convertToDTO(savedBoard);
    }
    
    // 게시글 수정
    @Transactional
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board existingBoard = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID가 " + id + "인 게시글을 찾을 수 없습니다"));
        
        existingBoard.setName(boardDTO.getName());
        existingBoard.setContents(boardDTO.getContents());
        existingBoard.preUpdate();
        
        Board updatedBoard = boardRepository.save(existingBoard);
        return convertToDTO(updatedBoard);
    }
    
    // 게시글 삭제
    @Transactional
    public void deleteBoard(Long id) {
        if (!boardRepository.existsById(id)) {
            throw new EntityNotFoundException("ID가 " + id + "인 게시글을 찾을 수 없습니다");
        }
        boardRepository.deleteById(id);
    }
    
    // 엔티티 -> DTO 변환
    private BoardDTO convertToDTO(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .contents(board.getContents())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
    
    // DTO -> 엔티티 변환
    private Board convertToEntity(BoardDTO boardDTO) {
        return Board.builder()
                .id(boardDTO.getId())
                .name(boardDTO.getName())
                .contents(boardDTO.getContents())
                .build();
    }
}