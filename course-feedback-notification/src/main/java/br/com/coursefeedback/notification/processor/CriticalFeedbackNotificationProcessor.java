
package br.com.coursefeedback.notification.processor;
import br.com.coursefeedback.notification.dto.NotificationMessageDTO;
import br.com.coursefeedback.notification.service.EmailService;

public class CriticalFeedbackNotificationProcessor {

        private final EmailService emailService;

        public CriticalFeedbackNotificationProcessor(EmailService emailService) {
            this.emailService = emailService;
        }

        public void process(NotificationMessageDTO notification) {

            emailService.send(notification);
        }
    }


