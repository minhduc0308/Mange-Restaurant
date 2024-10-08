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
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int paymentId;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    Orders orderId;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String paymentMethod;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date paymentDate;
    @Column(nullable = false)
    BigDecimal amount;
}
