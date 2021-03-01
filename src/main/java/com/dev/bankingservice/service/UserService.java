package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.User;

public interface UserService {
    User save(User user);

    User getById(Long id);

    User getByPhoneNumber(String phoneNumber);

    void deleteById(Long id);
}
