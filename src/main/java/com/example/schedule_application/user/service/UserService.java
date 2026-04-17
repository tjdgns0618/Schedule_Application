package com.example.schedule_application.user.service;

import com.example.schedule_application.user.dto.CreateUserRequest;
import com.example.schedule_application.user.dto.UpdateUserRequest;
import com.example.schedule_application.user.dto.UserResponse;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.exception.UserNotFoundException;
import com.example.schedule_application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private User userValidation(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Transactional
    public UserResponse signUp(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return UserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAllUser() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findOneUser(Long userId) {
        User findedUser = userValidation(userId);
        return UserResponse.from(findedUser);
    }


    public UserResponse update(Long userId, UpdateUserRequest request) {
        User user = userValidation(userId);
        user.updateUserDetails(request.getName(), request.getEmail());
        return UserResponse.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        userValidation(userId);
        userRepository.deleteById(userId);
    }
}
