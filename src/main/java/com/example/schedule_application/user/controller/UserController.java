package com.example.schedule_application.user.controller;

import com.example.schedule_application.user.dto.CreateUserRequest;
import com.example.schedule_application.user.dto.UpdateUserRequest;
import com.example.schedule_application.user.dto.UserAllDetailsResponse;
import com.example.schedule_application.user.service.UserService;
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

    @PostMapping
    public ResponseEntity<UserAllDetailsResponse> createUser(@RequestBody CreateUserRequest request) {
        UserAllDetailsResponse createdUserResponse = userService.signUp(request);
        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
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
            @PathVariable Long userId,
            @RequestBody UpdateUserRequest request
    ) {
        UserAllDetailsResponse userResponse = userService.update(userId, request);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
