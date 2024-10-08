package com.example.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reservationId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    Users customerId;
    @ManyToOne
    @JoinColumn(name = "tableId", nullable = false)
    Tables tableId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date reservationDate;
    @Column(nullable = false)
    int numberOfGuests;
    @ManyToOne
    @JoinColumn(name = "statusId", nullable = false)
    ReservationStatus revervationStatusId;
}
