package com.whs3.playground.dto;

import lombok.Data;


@Data
public class BoardRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;

    // 작성자 setter
    public void setAuthor(String author) {
        this.author = author;
    }
}
