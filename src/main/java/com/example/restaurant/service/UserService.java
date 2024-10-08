package com.example.restaurant.service;

import com.example.restaurant.dto.request.UserCreationRequest;
import com.example.restaurant.entities.Roles;
import com.example.restaurant.entities.UserRoles;
import com.example.restaurant.entities.Users;
import com.example.restaurant.mapper.UserMapper;
import com.example.restaurant.repository.RoleRepository;
import com.example.restaurant.repository.UserRepository;
import com.example.restaurant.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Users createUser(UserCreationRequest userCreationRequest) {
        if(userRepository.existsByUsername(userCreationRequest.getUsername())){
            throw new RuntimeException("Username already exist");
        }
        if(userRepository.existsByEmail(userCreationRequest.getEmail())){
            throw new RuntimeException("Email already exist");
        }
        if(userRepository.existsByPhone(userCreationRequest.getPhone())){
            throw new RuntimeException("Phone already exist");
        }
        Users users = userMapper.toUsers(userCreationRequest);
        users.setPasswordHash(passwordEncoder.encode(userCreationRequest.getPassword()));
        users.setStatus(1);
        Users savedUser = userRepository.save(users);
        Optional<Roles> roles = roleRepository.findByRoleName(userCreationRequest.getUsername());
        if(roles.isPresent()){
            UserRoles userRoles = new UserRoles();
            userRoles.setUser(savedUser);
            userRoles.setRole(roles.get());
            userRoleRepository.save(userRoles);
        }else{
            throw new RuntimeException("Role not found");
        }
        return savedUser;
    }
}
