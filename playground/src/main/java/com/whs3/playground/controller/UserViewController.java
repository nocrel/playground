package com.whs3.playground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserViewController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "user/loginForm";
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "user/signupForm";
    }
}
