package com.devteria.identityservice.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Total amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Total amount must be greater than 0")
    @Column(name = "total_amount", nullable = false)
    double totalAmount;

    @NotNull(message = "Invoice time cannot be null")
    @PastOrPresent(message = "Invoice time must be in the past or present")
    @Column(name = "invoice_time", nullable = false)
    LocalDateTime invoiceTime;

    @NotNull(message = "Order cannot be null")
    @OneToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;
}
