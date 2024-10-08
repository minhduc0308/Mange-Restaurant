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
public class InventoryTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transactionId;
    @ManyToOne
    @JoinColumn(name = "inventoryId", nullable = false)
    Inventory inventoryId;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String transactionType;

}
