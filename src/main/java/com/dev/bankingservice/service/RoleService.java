package com.dev.bankingservice.service;

import com.dev.bankingservice.entity.Role;

public interface RoleService {
    Role save(Role role);

    Role getByRoleName(String name);
}
