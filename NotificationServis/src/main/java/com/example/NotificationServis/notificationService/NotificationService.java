package com.example.NotificationServis.notificationService;

import com.example.NotificationServis.notificationTypesAndAdministration.Notification;
import com.example.NotificationServis.repository.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.NotificationServis.notificationTypesAndAdministration.NotificationType;

import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationTypeService notificationTypeService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, NotificationTypeService notificationTypeService) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeService = notificationTypeService;
    }

    public void sendActivationEmail(String recipientEmail, Map<String, String> parameters) throws ChangeSetPersister.NotFoundException {
        // Construct the email content using parameters
        String activationLink = parameters.get("activationLink");
        String emailContent = "Pozdrav " + parameters.get("ime") + " " + parameters.get("prezime") +
                ", da biste se verifikovali idite na sledeći link: " + activationLink;

        // Create a SimpleMailMessage
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Aktivacioni Email");
        mailMessage.setText(emailContent);

        // Send the email
        javaMailSender.send(mailMessage);

        // Save the notification to the database
        saveNotification(notificationTypeService.findByName("ACTIVATION_EMAIL"), recipientEmail, parameters);
    }


    public void sendPasswordChangeEmail(String recipientEmail, Map<String, String> parameters) throws EntityNotFoundException, ChangeSetPersister.NotFoundException {
        String passwordChangeLink = parameters.get("passwordChangeLink");
        String emailContent = "Pozdrav " + parameters.get("ime") + " " + parameters.get("prezime") +
                ", da biste promenili lozinku idite na sledeći link: " + passwordChangeLink;

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Promena Lozinke");
        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);

        NotificationType passwordChangeEmailType = notificationTypeService.findByName("PASSWORD_CHANGE_EMAIL");
        saveNotification(passwordChangeEmailType, recipientEmail, parameters);
    }

    public void sendTrainingScheduledNotification(String recipientEmail, Map<String, String> parameters) throws EntityNotFoundException, ChangeSetPersister.NotFoundException {
        // Add logic to send training scheduled notification and save the notification to the database
        // You can customize the email content based on the parameters
        String emailContent = "Pozdrav " + parameters.get("ime") + " " + parameters.get("prezime") +
                ", uspešno ste zakazali trening.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Zakazivanje Treninga");
        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);

        NotificationType trainingScheduledType = notificationTypeService.findByName("TRAINING_SCHEDULED");
        saveNotification(trainingScheduledType, recipientEmail, parameters);
    }

    public void sendTrainingCancellationNotification(String recipientEmail, Map<String, String> parameters) throws EntityNotFoundException, ChangeSetPersister.NotFoundException {
        // Add logic to send training cancellation notification and save the notification to the database
        // You can customize the email content based on the parameters
        String emailContent = "Pozdrav " + parameters.get("ime") + " " + parameters.get("prezime") +
                ", žao nam je, ali Vaš trening je otkazan.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Otkazivanje Treninga");
        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);

        NotificationType trainingCancellationType = notificationTypeService.findByName("TRAINING_CANCELLATION");
        saveNotification(trainingCancellationType, recipientEmail, parameters);
    }

    public void sendTrainingReminder(String recipientEmail, Map<String, String> parameters) throws EntityNotFoundException, ChangeSetPersister.NotFoundException {
        // Add logic to send training reminder and save the notification to the database
        // You can customize the email content based on the parameters
        String emailContent = "Pozdrav " + parameters.get("ime") + " " + parameters.get("prezime") +
                ", podsećamo Vas na predstojeći trening sutra u " + parameters.get("vreme") + ".";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Podsećanje na Trening");
        mailMessage.setText(emailContent);

        javaMailSender.send(mailMessage);

        NotificationType trainingReminderType = notificationTypeService.findByName("TRAINING_REMINDER");
        saveNotification(trainingReminderType, recipientEmail, parameters);
    }

    public List<Notification> getAllNotifications() {
        // Add logic to retrieve all notifications (admin only)
        return notificationRepository.findAll();
    }

    public List<Notification> getUserNotifications(String recipientEmail) {
        // Add logic to retrieve user-specific notifications
        return notificationRepository.findByRecipientEmail(recipientEmail);
    }

    private void saveNotification(NotificationType notificationType, String recipientEmail, Map<String, String> parameters) {
        // Add logic to save the notification to the database
        Notification notification = new Notification();
        notification.setNotificationType(notificationType);
        notification.setRecipientEmail(recipientEmail);
        notification.setParameters(parameters);
        notificationRepository.save(notification);
    }
}


