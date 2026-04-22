package com.example.schedule_application.user.service;

import com.example.schedule_application.common.config.PasswordEncoder;
import com.example.schedule_application.user.dto.LoginRequest;
import com.example.schedule_application.user.dto.SignUpRequest;
import com.example.schedule_application.user.dto.UpdateUserRequest;
import com.example.schedule_application.user.dto.UserAllDetailsResponse;
import com.example.schedule_application.user.entity.User;
import com.example.schedule_application.common.customException.DuplicateEmailException;
import com.example.schedule_application.common.customException.PasswordNotMatchException;
import com.example.schedule_application.common.customException.UserNotFoundException;
import com.example.schedule_application.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 존재하는 유저인지 검사
     *
     * @param userId 유저 고유 번호
     * @return 유저 데이터
     */
    @Transactional
    public User userValidation(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("존재하지 않는 유저입니다.")
        );
    }

    /**
     * 회원가입
     *
     * @param request 회원가입 하는 유저 데이터
     * @return 회원가입 완료된 유저 데이터(패스워드 제외)
     */
    @Transactional
    public UserAllDetailsResponse signUp(SignUpRequest request) {
        if (userRepository.existsByEmail(request.email()))
            throw new DuplicateEmailException();

        String encodedPassword = passwordEncoder.encode(request.password());

        User user = new User(request.name(), request.email(), encodedPassword);
        User savedUser = userRepository.save(user);

        return UserAllDetailsResponse.from(savedUser);
    }

    /**
     * 로그인
     *
     * @param request 로그인 유저 이메일, 패스워드
     * @return 로그인 완료된 유저 데이터(패스워드 제외)
     */
    @Transactional(readOnly = true)
    public User login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new UserNotFoundException("해당 유저를 찾을 수 없습니다.(존재하지 않는 ID 혹은 일치하지 않는 PASSWORD)")
        );

        boolean passwordMatch = passwordEncoder.matches(request.password(), user.getPassword());

        if (!passwordMatch) {
            throw new PasswordNotMatchException();
        }

        return user;
    }

    /**
     * 모든 유저 정보 조회
     *
     * @return 조회한 모든 유저 데이터 리스트
     */
    @Transactional(readOnly = true)
    public List<UserAllDetailsResponse> findAllUser() {
        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(UserAllDetailsResponse::from).toList();
    }

    /**
     * 유저 하나 데이터 조회
     *
     * @param userId 유저 고유 번호
     * @return 조회한 유저 데이터
     */
    @Transactional(readOnly = true)
    public UserAllDetailsResponse findOneUser(Long userId) {
        User findedUser = userValidation(userId);

        return UserAllDetailsResponse.from(findedUser);
    }

    /**
     * 유저 데이터 수정
     *
     * @param userId        유저 고유 번호
     * @param request       유저 수정 데이터
     * @return 수정된 유저 데이터
     */
    @Transactional
    public UserAllDetailsResponse updateUser(Long userId, UpdateUserRequest request) {

        User user = userValidation(userId);
        user.updateUserDetails(request.name(), request.email());

        return UserAllDetailsResponse.from(user);
    }

    /**
     * 유저 삭제
     *
     * @param userId 유저 고유 번호
     */
    @Transactional
    public void deleteUser(Long userId) {
        userValidation(userId);

        userRepository.deleteById(userId);
    }
}
