package com.dev.bankingservice.repository;

import com.dev.bankingservice.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.phoneNumber = ?1")
    Optional<User> getByPhoneNumber(String phoneNumber);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> getById(Long id);
}
