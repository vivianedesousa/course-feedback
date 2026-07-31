
package br.com.coursefeedback.notification.service;

import br.com.coursefeedback.notification.dto.NotificationMessageDTO;
import br.com.coursefeedback.feedback.domain.Feedback;
import br.com.coursefeedback.notification.sns.SnsPublisher;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CriticalFeedbackNotificationService {
    //  regra de negócio da notificação.
    private final SnsPublisher snsPublisher;

    public CriticalFeedbackNotificationService(SnsPublisher snsPublisher) {
        this.snsPublisher = snsPublisher;
    }

    public void publish(Feedback feedback) {

        NotificationMessageDTO message = new NotificationMessageDTO();

        message.setFeedbackId(feedback.getFeedbackId());
        message.setCourseId(feedback.getCourseId());
        message.setDescription(feedback.getDescription());
        message.setRating(feedback.getRating());
        message.setUrgency(feedback.getUrgency());
        message.setSubmittedAt(feedback.getCreatedAt());

        snsPublisher.publish(message);
    }
}
