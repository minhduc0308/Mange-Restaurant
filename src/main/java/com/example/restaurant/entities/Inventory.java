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
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int inventoryId;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String ingredientName;
    @Column(nullable = false)
    int quantity;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String unit;
    @Column(nullable = false)
    int threshold;
}
