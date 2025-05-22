package com.example.boardapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    
    private Long id;
    
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    
    @NotBlank(message = "내용을 입력해주세요")
    private String contents;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
