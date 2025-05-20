package com.whs3.playground.service;

import com.whs3.playground.dto.BoardRequestDto;
import com.whs3.playground.dto.BoardResponseDto;
import com.whs3.playground.dto.SuccessResponseDto;
import com.whs3.playground.entity.Board;
import com.whs3.playground.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    // 전체 게시물 조회
    @Transactional
    public List<BoardResponseDto> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    // 게시글 작성
    @Transactional
    public BoardResponseDto createPost(BoardRequestDto boardRequestDto) {
        Board board = new Board(boardRequestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 게시글 조회
    @Transactional
    public BoardResponseDto getPost(Long id) {
        return boardRepository.findById(id).map(BoardResponseDto::new).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
    }
    
    // 게시글 수정
    @Transactional
    public BoardResponseDto updatePost(Long id, BoardRequestDto boardRequestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );
        if (!boardRequestDto.getPassword().equals(board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        board.update(boardRequestDto);
        return new BoardResponseDto(board);
    }

    // 게시글 삭제
    @Transactional
    public SuccessResponseDto deletePost(Long id, BoardRequestDto boardRequestDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시글입니다.")
        );

        if (!boardRequestDto.getPassword().equals(board.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boardRepository.deleteById(id);
        return new SuccessResponseDto(true);
    }

}
