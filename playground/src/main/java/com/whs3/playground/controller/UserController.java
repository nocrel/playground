package com.whs3.playground.controller;

import com.whs3.playground.dto.UserRequestDto;
import com.whs3.playground.dto.UserSuccessResponseDto;
import com.whs3.playground.model.User;
import com.whs3.playground.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    // 로그아웃
    @GetMapping("/api/user/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok("로그아웃 되었습니다.");
    }

    // 비밀번호 초기화 API
    @PostMapping("/api/reset-password") // 인증없는 초기화
    public ResponseEntity<String> resetPassword(@RequestBody UserRequestDto userRequestDto) {
        if (userRequestDto == null || userRequestDto.getUserid() == null || userRequestDto.getUserid().isEmpty()) {
            return ResponseEntity.badRequest().body("올바르지 않은 접근입니다.");
        }

        try {
            String userid = userRequestDto.getUserid();
            userService.unsafeResetPassword(userid);

            return ResponseEntity.ok("비밀번호가 1111로 초기화되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            System.err.println("오류: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("오류입니다.");
        }

    }
}

