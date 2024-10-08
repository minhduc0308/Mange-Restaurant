package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int userID;

    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    String username;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String passwordHash;

    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    String email;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String fullName;

    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    String phone;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String address;

    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String userType;
    @Column(nullable = false)
    int status;
}
