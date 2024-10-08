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
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int tableId;
    @Column(nullable = false, unique = true)
    int taleNumber;
    @Column(nullable = false)
    int capacity;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String status;
}
