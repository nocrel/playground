package com.whs3.playground.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long usernum;
    private String userid;
    private String userpw;
    private String role; // ADMIN / PERSONAL / BUSINESS

    // 회원가입용 생성자
    public User(String userid, String userpw) {
        this.userid = userid;
        this.userpw = userpw;
        this.role = "USER";
    }
}
