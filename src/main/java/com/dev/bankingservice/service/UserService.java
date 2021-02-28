package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.User;

public interface UserService {
    User create(User user);

    User getById(Long id);

    User getByPhoneNumber(String phoneNumber);

    void update(User user);

    void delete(Long id);
}
