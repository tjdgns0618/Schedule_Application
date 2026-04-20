package com.example.schedule_application.user.repository;

import com.example.schedule_application.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users u WHERE BINARY u.email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
