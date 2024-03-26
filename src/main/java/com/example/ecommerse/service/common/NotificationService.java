package com.example.ecommerse.service.common;

import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.type.NotificationType;

public interface NotificationService {
    void saveNotification(User user, NotificationType notificationType);

}
