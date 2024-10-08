package com.example.restaurant.repository;

import com.example.restaurant.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
   boolean existsByUsername(String username);
   boolean existsByEmail(String email);
   boolean existsByPhone(String phone);
   Optional<Users> findByUsername(String username);
}
