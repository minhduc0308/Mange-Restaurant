package com.example.restaurant.configuration;

import com.example.restaurant.entities.Users;
import com.example.restaurant.enums.Role;
import com.example.restaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()){
                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());
                Users user = Users.builder()
                        .email("admin@gmail.com")
                        .username("admin")
                        .passwordHash(passwordEncoder.encode("admin"))
                        .phone("0896024712")
                        .address("Ha Noi")
                        .userType("ADMIN")
                        .status(1)
                        .fullName("admin")
                        .build();
                userRepository.save(user);
                log.warn("Default admin user has been created with default password: admin");
            }
        };
    }

}
