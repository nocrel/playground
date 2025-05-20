package com.whs3.playground.controller;

import com.whs3.playground.dto.BoardRequestDto;
import com.whs3.playground.dto.BoardResponseDto;
import com.whs3.playground.dto.SuccessResponseDto;
import com.whs3.playground.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class BoardController {

    private final BoardService boardService;

    // 전체 게시물 조회
    @GetMapping("/api/posts")
    public List<BoardResponseDto> getPosts() {
        return boardService.getPosts();
    }

    // 게시글 작성
    @PostMapping("/api/post")
    public BoardResponseDto createPost(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.createPost(boardRequestDto);
    }

    // 게시글 조회
    @GetMapping("/api/post/{id}")
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    // 게시글 수정
    @PutMapping("/api/post/{id}")
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) throws Exception {
        return boardService.updatePost(id, boardRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public SuccessResponseDto deletePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) throws Exception {
        return boardService.deletePost(id, boardRequestDto);
    }
}
