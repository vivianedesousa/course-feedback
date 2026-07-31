package br.com.coursefeedback.notification.service;
import br.com.coursefeedback.notification.dto.NotificationMessageDTO;

public class EmailService {
        public void send(NotificationMessageDTO notification) {
            System.out.println("=== EMAIL ===");
            System.out.println("Feedback: " + notification.getFeedbackId());
            System.out.println("Curso: " + notification.getCourseId());
            System.out.println("Descrição: " + notification.getDescription());
            System.out.println("Nota: " + notification.getRating());
            System.out.println("Urgência: " + notification.getUrgency());
            System.out.println("Data: " + notification.getSubmittedAt());
        }
    }

