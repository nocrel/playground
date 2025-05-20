package com.whs3.playground.repository;

import com.whs3.playground.model.User;

import java.util.Optional;

public interface UserRepository {
    Long save(User user); // 회원가입
    Optional<User> findByUserid(String userid); // ID로 조회 (로그인용)
}
