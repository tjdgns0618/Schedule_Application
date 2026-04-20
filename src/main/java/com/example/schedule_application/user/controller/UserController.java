package com.example.schedule_application.user.controller;

import com.example.schedule_application.user.dto.*;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.user.exception.LoginRequiredException;
import com.example.schedule_application.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserAllDetailsResponse> createUser(@Valid @RequestBody SignUpRequest request) {
        UserAllDetailsResponse createdUserResponse = userService.signUp(request);
        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        SessionUser sessionUser = SessionUser.from(user);
        session.setAttribute("loginUser", sessionUser);

        LoginResponse response = LoginResponse.from(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name = "loginUser", required = false) SessionUser sessionUser, HttpSession session) {
        validateSessionUser(sessionUser);
        session.invalidate();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<UserAllDetailsResponse>> getAllUsers() {
        List<UserAllDetailsResponse> userResponseList = userService.findAllUser();
        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserAllDetailsResponse> getOneUser(@PathVariable Long userId) {
        UserAllDetailsResponse userResponse = userService.findOneUser(userId);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserAllDetailsResponse> updateUser(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        validateSessionUser(sessionUser);
        UserAllDetailsResponse userResponse = userService.update(userId, request, sessionUser.getId());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    // Session 검사하고 삭제 진행하기
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @PathVariable Long userId,
            HttpSession session
    ) {
        validateSessionUser(sessionUser);
        userService.delete(userId, sessionUser.getId());
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void validateSessionUser(SessionUser sessionUser) {
        if(sessionUser == null) {
            throw new LoginRequiredException();
        }
    }
}
