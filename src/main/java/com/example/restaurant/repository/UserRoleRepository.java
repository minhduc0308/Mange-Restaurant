package com.example.restaurant.repository;

import com.example.restaurant.entities.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {
}
