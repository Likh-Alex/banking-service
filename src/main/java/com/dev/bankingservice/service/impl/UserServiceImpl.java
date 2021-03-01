package com.dev.bankingservice.service.impl;

import com.dev.bankingservice.entity.User;
import com.dev.bankingservice.repository.UserRepository;
import com.dev.bankingservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id).orElseThrow(()
                -> new RuntimeException("Can not get user by id: " + id));
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.getByPhoneNumber(phoneNumber).orElseThrow(()
                -> new RuntimeException("Can not get user by phone number: " + phoneNumber));
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
