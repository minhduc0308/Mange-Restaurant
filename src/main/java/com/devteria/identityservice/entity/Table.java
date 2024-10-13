package com.devteria.identityservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@jakarta.persistence.Table(name = "restaurant_table")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    @Column(name = "table_number", unique = true)
    int tableNumber;

    @NotNull
    @Column(name = "is_available")
    boolean isAvailable;

    @Column(name = "reservation_time")
    LocalDateTime reservationTime;

}
