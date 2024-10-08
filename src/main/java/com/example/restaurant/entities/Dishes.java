package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int dishId;
    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    String dishName;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String description;
    @Column(nullable = false)
    BigDecimal price;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String category;
}
