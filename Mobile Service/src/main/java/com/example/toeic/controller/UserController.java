package com.example.toeic.controller;

import com.example.toeic.model.dto.UserDTO;
import com.example.toeic.service.UserService;
import com.example.toeic.service.impl.UserDetailsServiceImpl;
import com.example.toeic.model.user.User;
import com.example.toeic.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/insert")
    public ResponseEntity<User> insert(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        if(user!=null) {
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.badRequest().build();
        }
    }
}
