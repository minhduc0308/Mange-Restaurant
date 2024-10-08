package com.example.restaurant.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReservationStatus {
    @Id
    @GeneratedValue
    int statusId;
    @Column(nullable = false, unique = true, columnDefinition = "nvarchar(255)")
    String statusName;
}
