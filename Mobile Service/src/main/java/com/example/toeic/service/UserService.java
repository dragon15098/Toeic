package com.example.toeic.service;

import com.example.toeic.model.dto.UserDTO;
import com.example.toeic.model.exception.UserExitsException;
import com.example.toeic.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    void createUser(UserDTO userDTO) throws UserExitsException;

}
