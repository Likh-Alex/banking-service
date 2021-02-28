package com.dev.bankingservice.repository;

import com.dev.bankingservice.entity.Role;
import javax.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Id> {
    @Query("SELECT r FROM Role r WHERE r.roleName = :name")
    Role getByName(@Param("name") Role.RoleName name);
}
