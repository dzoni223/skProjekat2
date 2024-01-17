package com.example.NotificationServis.notificationController;

import com.example.NotificationServis.notificationService.NotificationService;
import com.example.NotificationServis.notificationService.NotificationTypeService;
import com.example.NotificationServis.notificationTypesAndAdministration.Notification;
import com.example.NotificationServis.notificationTypesAndAdministration.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationTypeService notificationTypeService;
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationTypeService notificationTypeService, NotificationService notificationService) {
        this.notificationTypeService = notificationTypeService;
        this.notificationService = notificationService;
    }

    // Endpoint to create a new notification type
    @PostMapping("/types")
    public ResponseEntity<NotificationType> createNotificationType(@RequestBody NotificationType notificationType) {
        NotificationType createdType = notificationTypeService.createNotificationType(notificationType);
        return new ResponseEntity<>(createdType, HttpStatus.CREATED);
    }

    // Endpoint to get all notification types
    @GetMapping("/types")
    public ResponseEntity<List<NotificationType>> getAllNotificationTypes() {
        List<NotificationType> notificationTypes = notificationTypeService.getAllNotificationTypes();
        return new ResponseEntity<>(notificationTypes, HttpStatus.OK);
    }

    // Endpoint to get a specific notification type by ID
    @GetMapping("/types/{id}")
    public ResponseEntity<NotificationType> getNotificationTypeById(@PathVariable Long id) {
        NotificationType notificationType = notificationTypeService.getNotificationTypeById(id);
        if (notificationType != null) {
            return new ResponseEntity<>(notificationType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete a notification type by ID
    @DeleteMapping("/types/{id}")
    public ResponseEntity<Void> deleteNotificationType(@PathVariable Long id) {
        notificationTypeService.deleteNotificationType(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint to send activation email
    @PostMapping("/send-activation-email")
    public ResponseEntity<Void> sendActivationEmail(@RequestParam String recipientEmail, @RequestBody Map<String, String> parameters) throws ChangeSetPersister.NotFoundException {
        notificationService.sendActivationEmail(recipientEmail, parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to send password change email
    @PostMapping("/send-password-change-email")
    public ResponseEntity<Void> sendPasswordChangeEmail(@RequestParam String recipientEmail, @RequestBody Map<String, String> parameters) {
        notificationService.sendPasswordChangeEmail(recipientEmail, parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to send training scheduled notification
    @PostMapping("/send-training-scheduled-notification")
    public ResponseEntity<Void> sendTrainingScheduledNotification(@RequestParam String recipientEmail, @RequestBody Map<String, String> parameters) {
        notificationService.sendTrainingScheduledNotification(recipientEmail, parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to send training cancellation notification
    @PostMapping("/send-training-cancellation-notification")
    public ResponseEntity<Void> sendTrainingCancellationNotification(@RequestParam String recipientEmail, @RequestBody Map<String, String> parameters) {
        notificationService.sendTrainingCancellationNotification(recipientEmail, parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to send training reminder
    @PostMapping("/send-training-reminder")
    public ResponseEntity<Void> sendTrainingReminder(@RequestParam String recipientEmail, @RequestBody Map<String, String> parameters) {
        notificationService.sendTrainingReminder(recipientEmail, parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to get all notifications (admin only)
    @GetMapping("/all-notifications")
    public ResponseEntity<List<Notification>> getAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    // Endpoint to get user-specific notifications
    @GetMapping("/user-notifications")
    public ResponseEntity<List<Notification>> getUserNotifications(@RequestParam String recipientEmail) {
        List<Notification> userNotifications = notificationService.getUserNotifications(recipientEmail);
        return new ResponseEntity<>(userNotifications, HttpStatus.OK);
    }
}