package com.sbj.springbootjpa.service;

import com.sbj.springbootjpa.model.User;
import com.sbj.springbootjpa.model.dto.UserRequest;
import com.sbj.springbootjpa.model.dto.UserResponse;
import com.sbj.springbootjpa.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse addUser(UserRequest dto) {
        // dto를 entity로
        User user = dto.toEntity();
        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), "회원 등록 성공");
    }

    public UserResponse getUser(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isEmpty()) {
            return new UserResponse(id, "", "해당 id의 유저가 없습니다");
        } else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }
    }

}
