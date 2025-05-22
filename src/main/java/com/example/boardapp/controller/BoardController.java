package com.example.boardapp.controller;

import com.example.boardapp.dto.BoardDTO;
import com.example.boardapp.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService;
    
    // 게시글 목록 페이지
    @GetMapping
    public String listBoards(Model model) {
        model.addAttribute("boards", boardService.getAllBoards());
        return "board/list";
    }
    
    // 게시글 상세 페이지
    @GetMapping("/{id}")
    public String viewBoard(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.getBoardById(id));
        return "board/view";
    }
    
    // 게시글 작성 폼
    @GetMapping("/new")
    public String createBoardForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/form";
    }
    
    // 게시글 수정 폼
    @GetMapping("/{id}/edit")
    public String editBoardForm(@PathVariable Long id, Model model) {
        model.addAttribute("boardDTO", boardService.getBoardById(id));
        return "board/form";
    }
    
    // 게시글 저장 처리
    @PostMapping
    public String saveBoard(@Valid @ModelAttribute("boardDTO") BoardDTO boardDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "board/form";
        }
        
        BoardDTO savedBoard = boardService.saveBoard(boardDTO);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 저장되었습니다.");
        return "redirect:/boards/" + savedBoard.getId();
    }
    
    // 게시글 수정 처리
    @PostMapping("/{id}")
    public String updateBoard(@PathVariable Long id,
                             @Valid @ModelAttribute("boardDTO") BoardDTO boardDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "board/form";
        }
        
        boardService.updateBoard(id, boardDTO);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 수정되었습니다.");
        return "redirect:/boards/" + id;
    }
    
    // 게시글 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteBoard(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        boardService.deleteBoard(id);
        redirectAttributes.addFlashAttribute("message", "게시글이 성공적으로 삭제되었습니다.");
        return "redirect:/boards";
    }
}