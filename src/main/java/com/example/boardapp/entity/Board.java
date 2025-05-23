package com.example.boardapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
    
    @Id
    @SequenceGenerator(name = "board_seq", sequenceName = "boards_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq")
    private Long id;
    
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    
    @NotBlank(message = "내용을 입력해주세요")
    private String contents;
    
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    
    // 엔티티 생성 전/업데이트 전 실행
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }
    
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}