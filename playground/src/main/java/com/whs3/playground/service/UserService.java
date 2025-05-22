package com.whs3.playground.service;

import com.whs3.playground.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    // 회원가입
    Long signup(String userid, String userpw);

    // 로그인
    User login(String userid, String userpw);

    // 비밀번호 초기화
    void unsafeResetPassword(String userid);
}
