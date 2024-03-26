package com.example.ecommerse.model.entity;

import com.example.ecommerse.model.enums.type.NotificationType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "admin_notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private NotificationType subject;
    @OneToOne
    private User user;
}
