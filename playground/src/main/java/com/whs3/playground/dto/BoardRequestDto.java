package com.whs3.playground.dto;

import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}
