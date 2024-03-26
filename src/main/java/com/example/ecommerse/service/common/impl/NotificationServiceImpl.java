package com.example.ecommerse.service.common.impl;

import com.example.ecommerse.model.entity.AdminNotification;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.NotificationType;
import com.example.ecommerse.repository.AdminNotificationRepository;
import com.example.ecommerse.service.common.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final AdminNotificationRepository adminNotificationRepository;
    @Override
    public void saveNotification(User user, NotificationType notificationType) {
        AdminNotification notification = AdminNotification.builder()
                .subject(notificationType)
                .user(user).build();

        adminNotificationRepository.save(notification);
    }
}
