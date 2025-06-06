package com.dailyhelpers.helperservice.repository;

import com.dailyhelpers.helperservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhone(String phone);

    @Query("SELECT u FROM User u WHERE u.phone = :username")
    User findByUsername(@Param("username") String username);
}
