package com.sbj.springbootjpa.service;

import com.sbj.springbootjpa.model.User;
import com.sbj.springbootjpa.model.dto.UserRequest;
import com.sbj.springbootjpa.model.dto.UserResponse;
import com.sbj.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository); // 수동으로 DI
    }

    @Test
    void addTest() {
        Mockito.when(userRepository.save(any()))
                .thenReturn(new User(1L,"wbp","12341234"));

        UserResponse userResponse = userService.addUser(new UserRequest("wbp","12341234"));

        assertEquals("wbp",userResponse.getUsername());
        assertEquals("user 등록이 성공했습니다.", userResponse.getMessage());
    }
}