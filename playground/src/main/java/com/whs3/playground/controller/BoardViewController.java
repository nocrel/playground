package com.whs3.playground.controller;


import com.whs3.playground.dto.BoardResponseDto;
import com.whs3.playground.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/board")

public class BoardViewController {

    private final BoardService boardService;

    public BoardViewController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/posts")
    public String showPosts(Model model) {
        List<BoardResponseDto> posts = boardService.getPosts();
        model.addAttribute("posts", posts);
        return "board/showPosts";
    }

    @GetMapping("/post/{id}")
    public String showPost(@PathVariable Long id, Model model) {
        BoardResponseDto board = boardService.getPost(id);
        model.addAttribute("board", board);
        return "board/showPost";
    }

    @GetMapping("/create")
    public String createPost() {
        return "board/createPost";
    }

    @GetMapping("/edit/post/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        BoardResponseDto board = boardService.getPost(id);
        model.addAttribute("board", board);
        return "board/editPost";
    }

}
