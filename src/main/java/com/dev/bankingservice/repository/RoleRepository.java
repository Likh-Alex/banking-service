package com.dev.bankingservice.repository;

import com.dev.bankingservice.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> getRoleByName(@Param("name") Role.RoleName name);
}
