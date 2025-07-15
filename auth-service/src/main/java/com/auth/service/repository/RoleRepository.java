package com.auth.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.auth.service.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
