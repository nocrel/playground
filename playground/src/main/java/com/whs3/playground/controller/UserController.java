package com.whs3.playground.controller;

import com.whs3.playground.dto.UserRequestDto;
import com.whs3.playground.dto.UserSuccessResponseDto;
import com.whs3.playground.model.User;
import com.whs3.playground.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/api/user/signup")
    public UserSuccessResponseDto signup(@RequestBody UserRequestDto userRequestDto) {
        Long usernum = userService.signup(userRequestDto.getUserid(), userRequestDto.getUserpw());
        User user = new User();
        user.setUsernum(usernum);
        user.setUserid(userRequestDto.getUserid());
        user.setRole("USER");
        return new UserSuccessResponseDto(user);
    }

    // 로그인
    @PostMapping("/api/user/login")
    public UserSuccessResponseDto login(@RequestBody UserRequestDto userRequestDto, HttpServletRequest request) {
        User user = userService.login(userRequestDto.getUserid(), userRequestDto.getUserpw());

        // 세션에 회원 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("loggedInUser", user);
        session.setAttribute("userid", user.getUserid());

        return new UserSuccessResponseDto(user);
    }
}
