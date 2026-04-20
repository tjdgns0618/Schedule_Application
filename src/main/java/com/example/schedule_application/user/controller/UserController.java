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

    /**
     * 회원가입
     * @param request 회원가입에 필요한 데이터
     * @return CREATED 응답과 회원가입된 유저 데이터
     */
    @PostMapping("/signup")
    public ResponseEntity<UserAllDetailsResponse> createUser(@Valid @RequestBody SignUpRequest request) {
        UserAllDetailsResponse createdUserResponse = userService.signUp(request);

        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }

    /**
     * 로그인
     * @param request 로그인 요청 데이터
     * @param session 클라이언트 쿠키에 접근하기 위한 매개변수
     * @return OK 응답과 로그인된 유저의 비밀번호를 제외한 데이터
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        User user = userService.login(request);
        SessionUser sessionUser = SessionUser.from(user);
        session.setAttribute("loginUser", sessionUser);
        LoginResponse response = LoginResponse.from(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 로그아웃
     * @param session 클라이언트 쿠키에 접근하기 위한 매개변수
     * @return NO_CONTENT
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("loginUser");
        validateSessionUser(user);
        session.invalidate();

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * DB에 존재하는 모든 유저를 조회
     * @return DB에 존재하는 모든 유저 데이터 리스트
     */
    @GetMapping
    public ResponseEntity<List<UserAllDetailsResponse>> getAllUsers() {
        List<UserAllDetailsResponse> userResponseList = userService.findAllUser();

        return new ResponseEntity<>(userResponseList, HttpStatus.OK);
    }

    /**
     * 고유 번호에 해당하는 유저를 조회
     * @param userId 유저 고유 번호
     * @return 조회한 유저의 데이터
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserAllDetailsResponse> getOneUser(@PathVariable Long userId) {
        UserAllDetailsResponse userResponse = userService.findOneUser(userId);

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * 유저 정보를 수정
     * @param sessionUser 클라이언트의 로그인 상태를 서버에 저장하기 매개변수
     * @param userId      유저 고유 번호
     * @param request     수정 요청 데이터
     * @return 수정 완료된 유저 데이터
     */
    @PutMapping("/{userId}")
    public ResponseEntity<UserAllDetailsResponse> updateUser(
            @SessionAttribute(name = "loginUser", required = false)SessionUser sessionUser,
            @PathVariable Long userId,
            @Valid @RequestBody UpdateUserRequest request
    ) {
        validateSessionUser(sessionUser);
        UserAllDetailsResponse userResponse = userService.updateUser(userId, request, sessionUser.id());

        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    /**
     * 유저 삭제
     * @param userId  유저 고유 번호
     * @param session 클라이언트의 쿠키 데이터
     * @return NO_CONTENT
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long userId,
            HttpSession session
    ) {
        SessionUser user = (SessionUser) session.getAttribute("loginUser");
        validateSessionUser(user);
        userService.deleteUser(userId, user.id());
        session.invalidate();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * 쿠키에서 세션 정보가 존재하는지 검사
     * @param sessionUser 클라이언트의 쿠키 데이터
     */
    private void validateSessionUser(SessionUser sessionUser) {
        if(sessionUser == null) {
            throw new LoginRequiredException();
        }
    }
}
