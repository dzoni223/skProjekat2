package com.example.NotificationServis.notificationService;

import com.example.NotificationServis.notificationTypesAndAdministration.NotificationType;
import com.example.NotificationServis.repository.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationTypeService {

    private final NotificationTypeRepository notificationTypeRepository;

    @Autowired
    public NotificationTypeService(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationType createNotificationType(NotificationType notificationType) {
        // Add logic for validation or any other business rules
        return notificationTypeRepository.save(notificationType);
    }

    public List<NotificationType> getAllNotificationTypes() {
        return notificationTypeRepository.findAll();
    }

    public NotificationType getNotificationTypeById(Long id) {
        return notificationTypeRepository.findById(id).orElse(null);
    }

    public void deleteNotificationType(Long id) {
        notificationTypeRepository.deleteById(id);
    }

    public NotificationType findByName(String typeName) throws ChangeSetPersister.NotFoundException {
        return notificationTypeRepository.findByTypeName(typeName)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
}


