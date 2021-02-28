package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.Role;

public interface RoleService {
    Role create(Role role);

    Role getByName(String name);
}
