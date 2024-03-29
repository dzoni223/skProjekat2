package com.example.NotificationServis.repository;

import com.example.NotificationServis.notificationTypesAndAdministration.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    Optional<NotificationType> findByTypeName(String typeName);
}

