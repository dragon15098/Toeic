package com.example.toeic.service.impl;

import com.example.toeic.model.dto.UserDTO;
import com.example.toeic.model.exception.UserExitsException;
import com.example.toeic.model.user.User;
import com.example.toeic.repository.UserRepository;
import com.example.toeic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void createUser(UserDTO userDTO) throws UserExitsException {
        User user = userRepository.findByUsername(userDTO.getUsername());
        if (user == null) {
            User newUser = new User();
            newUser.setUsername(userDTO.getUsername());
            newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            userRepository.save(newUser);
            return;
        }
        throw new UserExitsException();
    }
}
