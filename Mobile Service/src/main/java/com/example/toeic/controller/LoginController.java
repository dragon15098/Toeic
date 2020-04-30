package com.example.toeic.controller;

import com.example.toeic.model.LoginRequest;
import com.example.toeic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/insert")
    public String insert(@Valid @RequestBody LoginRequest loginRequest) {
//        User user = new User();
//        user.setUsername("abc");
//        user.setPassword(passwordEncoder.encode("abc"));
//        userRepository.save(user);
        return "SUCCESS";
    }
}
