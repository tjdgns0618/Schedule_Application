package com.example.schedule_application.user.service;

import com.example.schedule_application.user.dto.CreateUserRequest;
import com.example.schedule_application.user.dto.UpdateUserRequest;
import com.example.schedule_application.user.dto.UserAllDetailsResponse;
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
    public UserAllDetailsResponse signUp(CreateUserRequest request) {
        User user = new User(request.getName(), request.getEmail());
        User savedUser = userRepository.save(user);
        return UserAllDetailsResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserAllDetailsResponse> findAllUser() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserAllDetailsResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public UserAllDetailsResponse findOneUser(Long userId) {
        User findedUser = userValidation(userId);
        return UserAllDetailsResponse.from(findedUser);
    }


    public UserAllDetailsResponse update(Long userId, UpdateUserRequest request) {
        User user = userValidation(userId);
        user.updateUserDetails(request.getName(), request.getEmail());
        return UserAllDetailsResponse.from(user);
    }

    @Transactional
    public void delete(Long userId) {
        userValidation(userId);
        userRepository.deleteById(userId);
    }
}
