package com.example.schedule_application.user.service;

import com.example.schedule_application.schedule.exception.NoPermissionException;
import com.example.schedule_application.user.dto.LoginRequest;
import com.example.schedule_application.user.dto.SignUpRequest;
import com.example.schedule_application.user.dto.UpdateUserRequest;
import com.example.schedule_application.user.dto.UserAllDetailsResponse;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.exception.DuplicateEmailException;
import com.example.schedule_application.user.exception.PasswordNotMatchException;
import com.example.schedule_application.user.exception.UserNotFoundException;
import com.example.schedule_application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User userValidation(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
    }

    public void validateOwner(Long userId, Long sessionUserId) {
        if(!Objects.equals(userId, sessionUserId)) {
            throw new NoPermissionException();
        }
    }

    @Transactional
    public UserAllDetailsResponse signUp(SignUpRequest request) {
        if(userRepository.existsByEmail(request.getEmail()))
            throw new DuplicateEmailException();

        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);
        return UserAllDetailsResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email());

        if(Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        if(!user.getPassword().equals(request.password())) {
            throw new PasswordNotMatchException();
        }

        return user;
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

    @Transactional
    public UserAllDetailsResponse update(Long userId, UpdateUserRequest request, Long sessionUserId) {
        validateOwner(userId, sessionUserId);
        User user = userValidation(userId);
        user.updateUserDetails(request.getName(), request.getEmail());
        return UserAllDetailsResponse.from(user);
    }

    @Transactional
    public void delete(Long userId, Long sessionUserId) {
        validateOwner(userId, sessionUserId);
        userValidation(userId);
        userRepository.deleteById(userId);
    }
}
