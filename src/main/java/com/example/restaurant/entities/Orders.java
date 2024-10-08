package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int orderId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    Users customerId;
    @ManyToOne
    @JoinColumn(name = "userId")
    Users userId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date orderDate;
    @Column(nullable = false)
    BigDecimal totalAmount;
    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    OrderStatus orderStatusId;
}
