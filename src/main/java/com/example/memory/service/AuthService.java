package com.example.memory.service;

import com.example.memory.proto.api.CreateUserRequestDTO;
import com.example.memory.proto.api.UserResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger LOGGER =  LoggerFactory.getLogger(UserService.class);
    public UserResponseDTO createUser(CreateUserRequestDTO dto) {
    }
}
