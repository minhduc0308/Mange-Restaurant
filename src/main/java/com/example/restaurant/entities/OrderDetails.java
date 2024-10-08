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
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderDetailId;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    Orders orderId;
    @ManyToOne
    @JoinColumn(name = "dishId", nullable = false)
    Dishes dishId;
    @Column(nullable = false)
    int quantity;
    @Column(nullable = false)
    BigDecimal price;
}
