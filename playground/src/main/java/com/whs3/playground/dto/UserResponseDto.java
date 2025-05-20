package com.whs3.playground.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.whs3.playground.model.User;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private Long usernum;
    private String userid;
    private String role;

    public UserResponseDto(User user) {
        this.usernum = user.getUsernum();
        this.userid = user.getUserid();
        this.role = user.getRole();
    }
}
