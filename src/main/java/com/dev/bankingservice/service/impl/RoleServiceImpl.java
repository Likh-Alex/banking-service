package com.dev.bankingservice.service.impl;

import com.dev.bankingservice.entity.Role;
import com.dev.bankingservice.repository.RoleRepository;
import com.dev.bankingservice.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getByRoleName(String name) {
        return roleRepository.getByRoleName(Role.RoleName.valueOf(name))
                .orElseThrow(() -> new RuntimeException("Can not get role by name :" + name));
    }
}
