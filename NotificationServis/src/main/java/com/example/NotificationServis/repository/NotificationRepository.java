package com.example.NotificationServis.repository;

import com.example.NotificationServis.notificationTypesAndAdministration.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientEmail(String recipientEmail);
    // Add any custom queries if needed
}
