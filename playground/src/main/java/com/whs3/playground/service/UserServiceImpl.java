package com.whs3.playground.service;

import com.whs3.playground.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.whs3.playground.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long signup(String userid, String userpw) {

        // 아이디 중복 검사
        Optional<User> existingUser = userRepository.findByUserid(userid);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // 회원 정보 저장
        User newUser = new User(userid, userpw);
        return userRepository.save(newUser);
    }

    @Override
    public User login(String userid, String userpw) {
        // 사용자 조회
        User user = userRepository.findByUserid(userid).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 아이디입니다.")
        );

        // 비밀번호 검증
        if (!user.getUserpw().equals(userpw)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공
        return user;
    }


}
