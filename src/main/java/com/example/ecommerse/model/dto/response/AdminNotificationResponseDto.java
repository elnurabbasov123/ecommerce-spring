package com.example.ecommerse.model.dto.response;

import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.NotificationType;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminNotificationResponseDto {
    private NotificationType subject;
    private Long userId;
}
