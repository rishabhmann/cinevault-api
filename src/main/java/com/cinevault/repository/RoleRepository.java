package com.cinevault.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cinevault.entity.Role;
import com.cinevault.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
}