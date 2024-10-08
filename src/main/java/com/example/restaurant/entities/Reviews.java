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
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int reviewId;
    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    Users userId;
    @Column(nullable = false)
    int rating;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String comment;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date reviewDate;
}
