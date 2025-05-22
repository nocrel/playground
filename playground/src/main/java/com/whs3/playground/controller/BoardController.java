package com.whs3.playground.controller;

import com.whs3.playground.dto.BoardRequestDto;
import com.whs3.playground.dto.BoardResponseDto;
import com.whs3.playground.dto.SuccessResponseDto;
import com.whs3.playground.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public BoardResponseDto createPost(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {

        // 세션에서 회원 정보 가져오기
        HttpSession session = request.getSession();
        String author = (String) session.getAttribute("userid");

        // 세션에 회원 정보가 없으면 예외 처리
        if (author == null) {
            throw new IllegalArgumentException("로그인 후 이용할 수 있는 기능입니다.");
        }

        // 세션 정보로 작성자 설정
        boardRequestDto.setAuthor(author);

        return boardService.createPost(boardRequestDto);
    }

    // 게시글 조회
    @GetMapping("/api/post/{id}")
    public BoardResponseDto getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    // 게시글 수정
    @PutMapping("/api/post/{id}")
    public BoardResponseDto updatePost(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) throws Exception {

        // 세션에서 회원 정보 가져오기
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute("userid");

        // 세션에 회원 정보가 없으면 예외 처리
        if (currentUser == null) {
            throw new IllegalArgumentException("로그인 후 이용할 수 있는 기능입니다.");
        }

        // 게시글 작성자 확인
        BoardResponseDto post = boardService.getPost(id);
        if (!post.getAuthor().equals(currentUser)) {
            throw new IllegalArgumentException("본인이 작성한 글만 수정할 수 있습니다.");
        }

        return boardService.updatePost(id, boardRequestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/post/{id}")
    public SuccessResponseDto deletePost(@PathVariable Long id, HttpServletRequest request) throws Exception {
        // 세션에서 회원 정보 가져오기
        HttpSession session = request.getSession();
        String currentUser = (String) session.getAttribute("userid");

        // 세션에 회원 정보가 없으면 예외 처리
        if (currentUser == null) {
            throw new IllegalArgumentException("로그인 후 이용할 수 있는 기능입니다.");
        }

        // 게시글 작성자 확인
        BoardResponseDto post = boardService.getPost(id);
        if (!post.getAuthor().equals(currentUser)) {
            throw new IllegalArgumentException("본인이 작성한 글만 삭제할 수 있습니다.");
        }

        return boardService.deletePost(id);
    }
}
