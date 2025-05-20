package com.whs3.playground.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.whs3.playground.model.User;

@Getter
@NoArgsConstructor
public class UserSuccessResponseDto {
    private boolean success;
    private Long usernum;
    private String role;

    public UserSuccessResponseDto(User user) {
        this.success = true;
        this.usernum = user.getUsernum();
        this.role = user.getRole();

    }
}
