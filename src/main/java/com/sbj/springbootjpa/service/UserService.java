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

        // 중복 체크 기능 구현
        // 저장하기 전 username으로 select를 하여 있는지 검사를 한다.
        Optional<User> checkUser = userRepository.findUserByUsername(dto.getUsername());

        if (checkUser.isEmpty()) {
            User savedUser = userRepository.save(user); //없는 username이면 저장해주고
            return new UserResponse(savedUser.getId(), savedUser.getUsername(), "user 등록이 성공했습니다.");
        } else {
            //중복 username이면
            return new UserResponse(checkUser.get().getId(), dto.getUsername(), "이미 존재하는 회원입니다.");
        }
    }

    public UserResponse getUser(Long id) {
        //1. id로 user 정보 검색
        Optional<User> optUser = userRepository.findById(id); //찾은 정보를 optUser에 담아

        if (optUser.isEmpty()) {
            return new UserResponse(id, "", "해당 id의 유저가 없습니다");
        } else {
            User user = optUser.get();
            return new UserResponse(user.getId(), user.getUsername(), "");
        }
    }

}
