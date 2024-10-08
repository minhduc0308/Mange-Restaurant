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
public class Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int notificationId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    Users userId;
    @Column(nullable = false, columnDefinition = "nvarchar(255)")
    String message;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    Date notificationDate;
    @Column(nullable = false)
    boolean isRead;
}
